#!/bin/sh -xe

export TERM=xterm
gosu postgres pg_ctl -D $PGDATA start

mkdir milliways-source/lib

cp -v marvin-source/build/libs/marvin-*.jar milliways-source/lib/

cd milliways-source

./gradlew --info test &&
    ./gradlew assemble
