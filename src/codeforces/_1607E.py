"""Codeforces 1607E

"""
import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    r, c = readln_int()
    S = input()


    def ok(x, xx, y, yy, i, j):
        x = min(x, i)
        xx = max(xx, i)
        y = min(y, j)
        yy = max(yy, j)
        return xx - x < r and yy - y < c


    x, xx, y, yy = 0, 0, 0, 0
    i, j = 0, 0
    for s in S:
        ii, jj = i, j
        if s == 'U':
            ii -= 1
        elif s == 'D':
            ii += 1
        elif s == 'L':
            jj -= 1
        else:
            jj += 1
        # print(s, (i, j), (ii, jj))
        if ok(x, xx, y, yy, ii, jj):
            i, j = ii, jj
            x = min(x, i)
            xx = max(xx, i)
            y = min(y, j)
            yy = max(yy, j)
    print(-x + 1, -y + 1)
