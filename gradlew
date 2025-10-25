#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle startup script for UN*X
##
##############################################################################

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass
# JVM options to this script.
DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Find java
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Find Gradle
if [ -n "$GRADLE_HOME" ] ; then
    if [ -x "$GRADLE_HOME/bin/gradle" ] ; then
        GRADLE_CMD="$GRADLE_HOME/bin/gradle"
    else
        die "ERROR: GRADLE_HOME is set to an invalid directory: $GRADLE_HOME

Please set the GRADLE_HOME variable in your environment to match the
location of your Gradle installation."
    fi
else
    GRADLE_WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
    if [ -f "$GRADLE_WRAPPER_JAR" ] ; then
        CLASSPATH="$GRADLE_WRAPPER_JAR"
        GRADLE_CMD="org.gradle.wrapper.GradleWrapperMain"
    else
        die "ERROR: Could not find gradle-wrapper.jar.

Please check that $APP_HOME is a valid directory."
    fi
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin ; then
    APP_HOME=`cygpath --path --windows "$APP_HOME"`
    CLASSPATH=`cygpath --path --windows "$CLASSPATH"`
fi

# Split up the JVM options again, in case they contain spaces
JVM_OPTS=
eval "set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS"
while [ $# -gt 0 ] ; do
    JVM_OPTS="$JVM_OPTS \"$1\""
    shift
done

# Execute Gradle
eval "\"$JAVACMD\"" $JVM_OPTS -classpath "\"$CLASSPATH\"" "-Dorg.gradle.appname=$APP_BASE_NAME" "$GRADLE_CMD" "$@"
