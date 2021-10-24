"""Codeforces 1593C

Greedy approach
1. If we want to save a mouse, it's always better to move it continuously to the hole.
2. Based on (1), it's always better to save the rightmost mouse.

"""
import sys

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    n, k = readln_int()
    A = sorted(readln_int())
    cat = 0

    ans = 0
    i = k - 1
    while i >= 0 and cat < A[i]:
        delta = n - A[i]
        ans += 1
        cat += delta
        i -= 1

    print(ans)
