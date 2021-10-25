"""Codeforces 1593E

"""
import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    input()

    N, K = readln_int()
    edges = [set() for _ in range(N)]
    deleted = [False] * N

    for _ in range(N - 1):
        u, v = readln_int()
        u, v = u - 1, v - 1 
        edges[u].add(v)
        edges[v].add(u)
    
    head, tail = 0, 0
    queue = set()
    for i in range(N):
        if len(edges[i]) <= 1:
            queue.add(i)

    for _ in range(K):
        if len(queue) == 0:
            break

        new_queue = set()
        for u in queue:
            deleted[u] = True
            for v in edges[u]:
                edges[v].discard(u)
                if not deleted[v] and len(edges[v]) <= 1:
                    new_queue.add(v)
        queue = new_queue

    ans = sum(1 for u in range(N) if not deleted[u])
    print(ans)
