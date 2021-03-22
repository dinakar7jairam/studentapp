#!/bin/bash
docker build . -t studentapp
mkdir -p build
docker run --rm --entrypoint cat studentapp  /home/application/function.zip > build/function.zip