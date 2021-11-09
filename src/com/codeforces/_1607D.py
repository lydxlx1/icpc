"""Codeforces 1607D

Red ball with value r can be treated as an interval [r, +inf).
Blue ball with value b can be treated as an interval (-inf, b].

Sort all red intervals in non-decreasing order.
Sort all blue intervals in non-decreasing order.

If we scan the permutation order from 1 to n, we can use a greedy approach.
That is, we always prefer to match the current permutation index with left-most
blue interval or left-most red interval (if no blue interval is satisfied).

The correctness is trivial when only red or blue intervals satisfy the condition.
Now, assume the permutation index i is covered by both a blue and a red interval,
(-inf, b] and [r, +inf).
Then, we must have r <= i <= b, and it is obvious to prefer the blue interval since
we are enumerating from 1 to n, i.e., the future permutation index can only be larger.
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
    C = input()

    red = []
    blue = []
    for i in range(n):
        if C[i] == 'R':
            red.append(A[i])
        else:
            blue.append(A[i])
    red.sort()
    blue.sort()

    ok = True
    i, j = 0, 0
    for p in range(1, n + 1):
        if j < len(blue) and blue[j] >= p:
            j += 1
        elif i < len(red) and red[i] <= p:
            i += 1
        else:
            ok = False
            break

    print('YES' if ok else 'NO')


