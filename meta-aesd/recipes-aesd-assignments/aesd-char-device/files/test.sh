#!/bin/bash

i=0
in_off=0
out_off=0
echo ""
while true; do
#  echo "Iteracja nr: $i"
  echo "IN: test$i" 
  echo "test$i"> /dev/aesdchar
  in_off=$((in_off+1))
  in_off=$((in_off%10))
  echo "in_off=$in_off out_off=$out_off"
  echo "OUT: $(cat /dev/aesdchar)"
  out_off=$((out_off+1))
  out_off=$((out_off%10))
  echo "in_off=$in_off out_off=$out_off"
#
  
  read -e
  i=$((i + 1))
  echo ""
  echo "******************************"
  echo ""
done
