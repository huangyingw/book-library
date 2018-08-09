#!/bin/bash

if [ "$1" = "build" ]
then
    './gradlew clean build'
fi

java -jar ./build/libs/*.jar