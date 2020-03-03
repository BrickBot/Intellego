#!/bin/bash

export INTELLEGO_HOME=/scratch/v15
export LEJOS_HOME=/usr/java/lejos/
export PATH=${PATH}:$LEJOS_HOME/bin
export CLASSPATH=$LEJOS_HOME/lib/pcrcxcomm.jar:$LEJOS_HOME/lib/classes.jar:.:$INTELLEGO_HOME:$INTELLEGO_HOME/controllers:$INTELLEGO_HOME/simworlds:$INTELLEGO_HOME/real:${CLASSPATH}

cd $INTELLEGO_HOME
java intellego.Intellego
