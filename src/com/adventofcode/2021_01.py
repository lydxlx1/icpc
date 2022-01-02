import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]

with open('in.txt', 'r') as fin:
    lines = fin.read().strip().split("\n")
    nums = [int(i) for i in lines]
    cnt = 0
    for i in range(1, len(nums)):
        if nums[i-1] < nums[i]:
            cnt +=1 
    print(cnt)










