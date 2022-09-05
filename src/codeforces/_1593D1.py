"""Codeforces 1593D1

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
    A = readln_int()
    m = min(A)
    A = [i - m for i in A if i > m]

    if len(A) == 0:
        print(-1)
    else:
        g = A[0]
        for i in A:
            g = math.gcd(g, i)
        print(g)
        

    



