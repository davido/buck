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

package com.facebook.buck.file;

import com.facebook.buck.rules.AbstractDescriptionArg;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.util.HumanReadableException;
import com.facebook.infer.annotation.SuppressFieldNotInitialized;
import com.google.common.hash.HashCode;

import java.net.URI;
import java.util.Optional;

public class RemoteFileDescription implements Description<RemoteFileDescription.Arg> {

  private final Downloader downloader;

  public RemoteFileDescription(Downloader downloader) {
    this.downloader = downloader;
  }

  @Override
  public Arg createUnpopulatedConstructorArg() {
    return new Arg();
  }

  @Override
  public <A extends Arg> BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      A args) {
    HashCode sha1;
    try {
      sha1 = HashCode.fromString(args.sha1);
    } catch (IllegalArgumentException e) {
      throw new HumanReadableException(
          e,
          "%s when parsing sha1 of %s",
          e.getMessage(),
          params.getBuildTarget().getUnflavoredBuildTarget().getFullyQualifiedName());
    }

    String out = args.out.orElse(params.getBuildTarget().getShortNameAndFlavorPostfix());

    RemoteFile.Type type = args.type.orElse(RemoteFile.Type.DATA);
    if (type == RemoteFile.Type.EXECUTABLE) {
      return new RemoteFileBinary(params, downloader, args.url, sha1, out, type);
    }
    return new RemoteFile(params, downloader, args.url, sha1, out, type);
  }

  @SuppressFieldNotInitialized
  public static class Arg extends AbstractDescriptionArg {
    public URI url;
    public String sha1;
    public Optional<String> out;
    public Optional<RemoteFile.Type> type;
  }
}
