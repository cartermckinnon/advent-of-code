#!/usr/bin/env bash

find . -type f -name '*.java' -exec google-java-format --replace {} +

