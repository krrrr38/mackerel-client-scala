#!/bin/sh

sbt ++2.11.7 \
    clean \
    publishSigned \
    ++2.10.5 \
    clean \
    publishSigned
