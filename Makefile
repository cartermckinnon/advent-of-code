YEAR ?= 2022
DAYS ?= $(shell ./day-sequence.sh)

.PHONY: run
run:
	for DAY in $(DAYS); do \
	  echo "---DAY-$$DAY---" ; \
	  for FILE in src/$(YEAR)/$$DAY/*.java; do \
	    java $$FILE src/$(YEAR)/$$DAY/input.txt ; \
	  done \
	done
