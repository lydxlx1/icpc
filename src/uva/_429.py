"""
UVa 429 - Word Transformation
"""

from typing import *

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


for case in range(int(next())):
    if case > 0:
        print()

    words = []
    while True:
        s = next()
        if s == '*':
            break
        words.append(s)

    g = {}
    for u in words:
        g[u] = []
        for v in words:
            if len(u) == len(v) and len([1 for i in range(len(u)) if u[i] != v[i]]) == 1:
                g[u].append(v)

    try:
        while True:
            row = input()
            if not row.strip():
                break
            start, end = row.split()


            def bfs() -> int:
                dist = {start: 0}
                queue = Deque()
                queue.append(start)
                while queue:
                    u = queue.popleft()
                    if u == end:
                        return dist[u]

                    for v in g[u]:
                        if v not in dist:
                            dist[v] = dist[u] + 1
                            queue.append(v)


            print("{} {} {}".format(start, end, bfs()))
    except EOFError:
        break

