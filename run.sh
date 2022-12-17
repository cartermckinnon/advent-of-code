#!/usr/bin/env bash

set -o errexit
set -o nounset

cd "$(dirname "$0")"
mkdir -p build

function run() {
  local YEAR="$1"
  local DAY="$2"
  echo "-> $YEAR/$DAY"
  if [ ! -d src/year$YEAR/day$DAY ]; then
    echo "No solution yet!"
    return
  fi
  DIR=src/year$YEAR/day$DAY
  javac -d build/$DIR $DIR/*.java
  for FILE in $DIR/*.java; do
    if grep "public static void main" $FILE > /dev/null; then
      CLASS_NAME=$(basename $FILE | cut -f1 -d".")
      echo "--> $CLASS_NAME"
      java -cp build/$DIR year$YEAR.day$DAY.$CLASS_NAME $DIR/input.txt
    fi
  done
}

if [ "$#" -eq 0 ]; then
  cd "$(dirname "$0")"
  YEAR=2022
  DAYS=$(./day-sequence.sh)
  for DAY in $DAYS; do
    run $YEAR $DAY
  done
elif [ "$#" -eq 2 ]; then
  run "$1" "$2"
else
  echo "usage: $0 YEAR DAY"
  exit 1
fi
