"""
UVa 336 - A Node Too Far
"""

from typing import *
from collections import *

cin_buffer = []
cin_buffer_ptr = 0


def next() -> str:
    global cin_buffer
    global cin_buffer_ptr
    if cin_buffer_ptr >= len(cin_buffer):
        cin_buffer = input().split()
        cin_buffer_ptr = 0
        return next()
    else:
        res = cin_buffer[cin_buffer_ptr]
        cin_buffer_ptr += 1
        return res


case = 0
while True:
    m = int(next())
    if m == 0:
        break

    g = defaultdict(list)
    for _ in range(m):
        u, v = int(next()), int(next())
        g[u].append(v)
        g[v].append(u)

    while True:
        start, ttl = int(next()), int(next())
        if start == 0 and ttl == 0:
            break

        dist = {start: 0}
        queue = Deque()
        queue.append(start)
        while queue:
            u = queue.popleft()
            for v in g[u]:
                if v not in dist and dist[u] + 1 <= ttl:
                    dist[v] = dist[u] + 1
                    queue.append(v)
        not_reachable = len(set(g.keys()) - set(dist.keys()))
        case += 1
        print("Case {case}: {not_reachable} nodes not reachable from node {start} with TTL = {ttl}.".format(**locals()))

