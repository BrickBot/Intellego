#!/bin/bash

rm ../HTMLcode -rf
mkdir ../HTMLcode
export CLASSPATH=${CLASSPATH}:util/j2h.jar
cd ..
java j2h -js . -d HTMLcode

