"""Codeforces 1593A
"""
import sys


T = eval(input())
for _ in range(T):
    A = [int(i) for i in input().split(" ")]
    a, b, c = A
    A = sorted(A)

    if (A[1] == A[2]):
        a = A[2] + 1 - a
        b = A[2] + 1 - b
        c = A[2] + 1 - c
    else:
        a = A[2] + 1 - a if a < A[2] else 0
        b = A[2] + 1 - b if b < A[2] else 0
        c = A[2] + 1 - c if c < A[2] else 0

    print(a, b, c)

# input()
# input()
# input()
# input()

