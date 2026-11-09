#!/usr/bin/env bash
# Temporary diagnostic: how does ParaBank answer a wrong login from a fresh client?
B=https://parabank.parasoft.com/parabank
strip() { sed -e 's/<[^>]*>/ /g' | tr -s ' \n' | grep -oE "(Welcome[^|]{0,40}|could not be verified[^|]{0,10}|Please enter[^|]{0,40}|Accounts Overview|internal error[^|]{0,20}|Error![^|]{0,40})" | sort -u; }
for i in 1 2; do
  J=$(mktemp)
  curl -s -c $J -b $J $B/index.htm >/dev/null
  echo "== wrong login #$i"; curl -s -L -c $J -b $J -d "username=nobody_rb_9x7q$i&password=wrongPass1" $B/login.htm | strip
  echo "== overview with same cookies"; curl -s -L -c $J -b $J $B/overview.htm | strip
  K=$(mktemp)
  echo "== overview with NO cookies"; curl -s -L -c $K -b $K $B/overview.htm | strip
done
