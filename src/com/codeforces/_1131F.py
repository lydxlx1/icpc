"""Codeforces 1131F

Somehow, recursive implementation of Union-Find Algorithm will hit stack over...
"""
from sys import *

n = int(stdin.readline())

parent, L, R, left, right = {}, {}, {}, {}, {}
for i in range(1, n + 1):
    parent[i] = i
    L[i] = R[i] = i
    left[i] = right[i] = None


def find(i):
    root = i
    while parent[root] != root:
        root = parent[root]
    while i != root:
        j = parent[i]
        parent[i] = root
        i = j
    return root


def union(i, j):
    i, j = find(i), find(j)
    parent[i] = j  # j is the new root

    a, b, c, d = L[i], R[i], L[j], R[j]
    L[j], R[j] = a, d
    right[b], left[c] = c, b


for i in range(n - 1):
    u, v = stdin.readline().split(' ')
    union(int(u), int(v))

ans = []
root = L[find(1)]
for i in range(n):
    ans.append(root)
    root = right[root]

print(' '.join([str(i) for i in ans]))

