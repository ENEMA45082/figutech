#!/bin/sh
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

set -euf
[ "${DEBUG-}" ] && set -x

WRAPPER_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
PROJECT_DIR="$WRAPPER_DIR"

if [ -n "${MAVEN_PROJECTBASEDIR-}" ]; then
  PROJECT_DIR="$MAVEN_PROJECTBASEDIR"
fi

MAVEN_WRAPPER_PROPERTIES="$PROJECT_DIR/.mvn/wrapper/maven-wrapper.properties"
MAVEN_WRAPPER_JAR="$PROJECT_DIR/.mvn/wrapper/maven-wrapper.jar"

if [ ! -f "$MAVEN_WRAPPER_JAR" ]; then
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$MAVEN_WRAPPER_JAR" "$(grep -E '^wrapperUrl=' "$MAVEN_WRAPPER_PROPERTIES" | cut -d= -f2-)"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O "$MAVEN_WRAPPER_JAR" "$(grep -E '^wrapperUrl=' "$MAVEN_WRAPPER_PROPERTIES" | cut -d= -f2-)"
  else
    echo "Error: Need curl or wget to download Maven Wrapper jar." >&2
    exit 1
  fi
fi

exec java -cp "$MAVEN_WRAPPER_JAR" -Dmaven.multiModuleProjectDirectory="$PROJECT_DIR" org.apache.maven.wrapper.MavenWrapperMain "$@"
