#!/bin/bash

cd ..
javadoc -author -d javadocs intellego/*.java controllers/*.java hybrid/*.java interfaces/*.java main/*.java real/*.java simworldobjects/*.java simworlds/*.java util/*.java 2>/dev/null

