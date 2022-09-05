"""Codeforces 1593D2

"""
import sys
import math
from collections import Counter

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    n = int(input())
    A = sorted(readln_int())
    D = Counter(A)

    if max(D.values()) >= n // 2:
        print(-1)
    else:
        ans = 1
        for i in range(D):
            A = [ j - i for j in A if j > i ]
            need = n // 2 - D[i]

