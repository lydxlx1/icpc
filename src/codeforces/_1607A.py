"""Codeforces 1607A

"""
import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    d = input()
    pos = {}
    for i in range(26):
        pos[d[i]] = i
   
    s = input()
    ans = 0
    for i in range(len(s) - 1):
        ci, cici = s[i], s[i+1]
        ans += abs(pos[ci] - pos[cici])
    print(ans)
