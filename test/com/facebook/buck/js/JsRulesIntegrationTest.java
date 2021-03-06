/*
 * Copyright 2017-present Facebook, Inc.
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

package com.facebook.buck.js;

import static org.junit.Assume.assumeFalse;

import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.testutil.integration.ProjectWorkspace;
import com.facebook.buck.testutil.integration.TemporaryPaths;
import com.facebook.buck.testutil.integration.TestDataHelper;
import com.facebook.buck.util.environment.Platform;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsRulesIntegrationTest {

  @Rule
  public TemporaryPaths tmp = new TemporaryPaths();

  private ProjectWorkspace workspace;
  private ProjectFilesystem projectFilesystem;
  private Path genPath;

  @Before
  public void setUp() throws IOException {
    // worker tool does not work on windows
    assumeFalse(Platform.detect() == Platform.WINDOWS);
    workspace = TestDataHelper.createProjectWorkspaceForScenario(
        this,
        "js_rules",
        tmp);
    workspace.setUp();
    projectFilesystem = new ProjectFilesystem(workspace.getDestPath());
    genPath = projectFilesystem.getBuckPaths().getGenDir();
  }

  @Test
  public void testSimpleLibraryBuild() throws IOException {
    workspace
        .runBuckBuild("//js:fruit")
        .assertSuccess();

    workspace.verify(Paths.get("simple_library_build.expected"), genPath);
  }

  @Test
  public void testBuildWithExtraArgs() throws IOException {
    workspace
        .runBuckBuild("//js:extras")
        .assertSuccess();

    workspace.verify(Paths.get("with_extra_args.expected"), genPath);
  }

  @Test
  public void testOptimizationBuild() throws IOException {
    workspace
        .runBuckBuild("//js:fruit#prod,android")
        .assertSuccess();

    workspace.verify(Paths.get("simple_prod_build.expected"), genPath);
  }

  @Test
  public void testBuildWithDeps() throws IOException {
    workspace
        .runBuckBuild("//js:fruit-salad")
        .assertSuccess();

    workspace.verify(Paths.get("with_deps.expected"), genPath);
  }

  @Test
  public void testProdBuildWithDeps() throws IOException {
    workspace
        .runBuckBuild("//js:fruit-salad#prod,ios")
        .assertSuccess();

    workspace.verify(Paths.get("prod_flavor_with_deps.expected"), genPath);
  }

  @Test
  public void testFlavoredAndUnflavoredBuild() throws IOException {
    workspace
        .runBuckBuild("//js:fruit#prod,android", "//js:fruit")
        .assertSuccess();

    workspace.verify(Paths.get("same_target_with_and_without_flavors.expected"), genPath);
  }
}
