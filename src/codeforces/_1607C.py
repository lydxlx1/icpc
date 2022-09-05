"""Codeforces 1607C

"""
import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    n = int(input())
    A = sorted(readln_int())
    ans = A[0]

    prefix = -A[0]
    for i in range(1, n):
        ans = max(ans, A[i] + prefix)
        prefix -= A[i] + prefix

    print(ans)     







