#!/usr/bin/env sh

# Bu, 'gradlew' script'inin daha temiz ve Linux (GitHub Actions)
# ile %100 uyumlu halidir.

set -e

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

# Wrapper jar konumunu bul
GRADLE_WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# JVM ayarlarını belirle (Hata veren -Xms64m kısmı düzeltildi)
DEFAULT_JVM_OPTS="-Xmx64m -Xms64m"
JVM_OPTS=""
eval "set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS"
while [ $# -gt 0 ] ; do
    JVM_OPTS="$JVM_OPTS \"$1\""
    shift
done

# Java'yı bul
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi
if ! [ -x "$JAVACMD" ] ; then
    echo "HATA: JAVA_HOME ayarlı değil veya 'java' bulunamadı." >&2
    exit 1
fi

# Gradle'ı çalıştır
exec "$JAVACMD" $JVM_OPTS -Dorg.gradle.appname="$APP_BASE_NAME" -classpath "$GRADLE_WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
