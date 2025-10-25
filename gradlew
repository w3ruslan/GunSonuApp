#!/usr/bin/env sh

# Bu, 'gradlew' script'inin JVM ayar hatası düzeltilmiş
# en basit ve %100 Linux (GitHub Actions) uyumlu halidir.

set -e

# Resolve links: $0 may be a link
PRG="$0"
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

APP_BASE_NAME=`basename "$0"`

# Wrapper jar konumunu bul
GRADLE_WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"

# --- HATA BURADAYDI, DÜZELTİLDİ ---
# Varsayılan JVM ayarları (bu ayarları Java komutuna tırnaksız ileteceğiz)
DEFAULT_JVM_OPTS="-Xmx64m -Xms64m"

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
# ÖNEMLİ DÜZELTME: $DEFAULT_JVM_OPTS, $JAVA_OPTS, ve $GRADLE_OPTS
# tırnak (" ") içinde OLMADAN, doğrudan komuta iletiliyor.
exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS -Dorg.gradle.appname="$APP_BASE_NAME" -classpath "$GRADLE_WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
