/*
 * Copyright 2014-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.thrift;

import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.Flavor;
import com.facebook.buck.model.ImmutableFlavor;
import com.facebook.buck.python.PythonLibrary;
import com.facebook.buck.python.PythonPlatform;
import com.facebook.buck.python.PythonUtil;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.ExplicitBuildTargetSourcePath;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.util.HumanReadableException;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.io.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ThriftPythonEnhancer implements ThriftLanguageSpecificEnhancer {

  private static final Flavor PYTHON_FLAVOR = ImmutableFlavor.of("py");
  private static final Flavor PYTHON_TWISTED_FLAVOR = ImmutableFlavor.of("py-twisted");
  private static final Flavor PYTHON_ASYNCIO_FLAVOR = ImmutableFlavor.of("py-asyncio");

  public enum Type {
    NORMAL,
    TWISTED,
    ASYNCIO,
  }

  private final ThriftBuckConfig thriftBuckConfig;
  private final Type type;

  public ThriftPythonEnhancer(ThriftBuckConfig thriftBuckConfig, Type type) {
    this.thriftBuckConfig = thriftBuckConfig;
    this.type = type;
  }

  @Override
  public String getLanguage() {
    return "py";
  }

  @Override
  public Flavor getFlavor() {
    switch (type) {
      case NORMAL:
        return PYTHON_FLAVOR;
      case TWISTED:
        return PYTHON_TWISTED_FLAVOR;
      case ASYNCIO:
        return PYTHON_ASYNCIO_FLAVOR;
    }
    throw new IllegalStateException();
  }

  @Override
  public ImmutableSortedSet<String> getGeneratedSources(
      BuildTarget target,
      ThriftConstructorArg args,
      String thriftName,
      ImmutableList<String> services) {

    Path prefix =
        PythonUtil.getBasePath(target, getBaseModule(args))
            .resolve(Files.getNameWithoutExtension(thriftName));

    ImmutableSortedSet.Builder<String> sources = ImmutableSortedSet.naturalOrder();

    sources.add(prefix.resolve("constants.py").toString());
    sources.add(prefix.resolve("ttypes.py").toString());

    for (String service : services) {
      sources.add(prefix.resolve(service + ".py").toString());
      if (type == Type.NORMAL) {
        sources.add(prefix.resolve(service + "-remote").toString());
      }
    }

    return sources.build();
  }

  private Optional<String> getBaseModule(ThriftConstructorArg args) {
    switch (type) {
      case NORMAL:
        return args.pyBaseModule;
      case TWISTED:
        return args.pyTwistedBaseModule;
      case ASYNCIO:
        return args.pyAsyncioBaseModule;
    }
    throw new IllegalStateException(String.format("Unexpected python thrift type: %s", type));
  }

  @Override
  public PythonLibrary createBuildRule(
      TargetGraph targetGraph,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      ThriftConstructorArg args,
      ImmutableMap<String, ThriftSource> sources,
      ImmutableSortedSet<BuildRule> deps) {

    ImmutableMap.Builder<Path, SourcePath> modulesBuilder = ImmutableMap.builder();

    // Iterate over all the thrift source, finding the python modules they generate and
    // building up a map of them.
    for (ImmutableMap.Entry<String, ThriftSource> ent : sources.entrySet()) {
      ThriftSource source = ent.getValue();
      Path outputDir = source.getOutputDir(resolver);

      for (String module :
           getGeneratedSources(params.getBuildTarget(), args, ent.getKey(), source.getServices())) {
        Path path = outputDir
            .resolve("gen-" + getLanguage())
            .resolve(module);
        modulesBuilder.put(
            Paths.get(module.endsWith(".py") ? module : module + ".py"),
            new ExplicitBuildTargetSourcePath(source.getCompileTarget(), path));
      }

    }

    ImmutableMap<Path, SourcePath> modules = modulesBuilder.build();

    // Create params which only use the language specific deps.
    BuildRuleParams langParams = params.copyWithChanges(
        params.getBuildTarget(),
        Suppliers.ofInstance(deps),
        Suppliers.ofInstance(ImmutableSortedSet.of()));

    // Construct a python library and return it as our language specific build rule.  Dependents
    // will use this to pull the generated sources into packages/PEXs.
    Function<? super PythonPlatform, ImmutableMap<Path, SourcePath>> resources =
        Functions.constant(ImmutableMap.<Path, SourcePath>of());
    return new PythonLibrary(
        langParams,
        Functions.constant(modules),
        resources,
        Optional.of(true));
  }

  private ImmutableSet<BuildTarget> getImplicitDeps() {
    ImmutableSet.Builder<BuildTarget> implicitDeps = ImmutableSet.builder();

    implicitDeps.add(thriftBuckConfig.getPythonDep());

    if (type == Type.TWISTED) {
      implicitDeps.add(thriftBuckConfig.getPythonTwistedDep());
    } else if (type == Type.ASYNCIO) {
      implicitDeps.add(thriftBuckConfig.getPythonAsyncioDep());
    }

    return implicitDeps.build();
  }

  @Override
  public ImmutableSet<BuildTarget> getImplicitDepsForTargetFromConstructorArg(
      BuildTarget target,
      ThriftConstructorArg arg) {
    return getImplicitDeps();
  }

  @Override
  public ImmutableSet<String> getOptions(
      BuildTarget target,
      ThriftConstructorArg args) {

    // Don't allow the "twisted" or "asyncio" parameters to be passed in via the options
    // parameter, as we use dedicated flavors to handle them.
    if (args.pyOptions.contains("twisted")) {
      throw new HumanReadableException(
          "%s: parameter \"py_options\": cannot specify \"twisted\" as an option" +
              " -- use the \"#%s\" flavor instead",
          target,
          PYTHON_TWISTED_FLAVOR);
    }
    if (args.pyOptions.contains("asyncio")) {
      throw new HumanReadableException(
          "%s: parameter \"py_options\": cannot specify \"asyncio\" as an option" +
              " -- use the \"#%s\" flavor instead",
          target,
          PYTHON_ASYNCIO_FLAVOR);
    }

    ImmutableSet.Builder<String> options = ImmutableSet.builder();
    options.addAll(args.pyOptions);

    if (type == Type.TWISTED) {
      options.add("twisted");
    } else if (type == Type.ASYNCIO) {
      options.add("asyncio");
    }

    return options.build();
  }

  @Override
  public ThriftLibraryDescription.CompilerType getCompilerType() {
    return ThriftLibraryDescription.CompilerType.THRIFT;
  }

}
