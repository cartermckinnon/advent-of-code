#!/usr/bin/env bash

SECONDS=$(date -u '+%s')
# 12/25 12:00:00 UTC
if [ "$SECONDS" -lt 1671926400 ]; then
  END=$(date -u '+%d')
else
  END=25
fi

echo "$(seq -s ' ' 1 $END)"
