"""Codeforces 1131B
"""
from sys import *

n = int(stdin.readline())
ans = 1
a, b = 0, 0

last = 0
for i in range(n):
    aa, bb = [int(i) for i in stdin.readline().split(' ')]
    # [a, aa]
    # [b, bb]
    x, y = max(a, b, last + 1), min(aa, bb)
    ans += max(y - x + 1, 0)
    last = y

    a, b = aa, bb

print(ans)

