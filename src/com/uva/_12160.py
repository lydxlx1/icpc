"""
UVa 12160 - Unlock the Lock
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


def next_int() -> int:
    return int(next())


case = 0
while True:
    start, end, m = next_int(), next_int(), next_int()
    if start == 0 and end == 0 and m == 0:
        break
    addons = [next_int() for i in range(m)]

    ans = "Permanently Locked"

    dist, queue = {start: 0}, Deque([start])
    while queue:
        u = queue.popleft()
        if u == end:
            ans = dist[u]
            break
        for addon in addons:
            v = (u + addon) % 10000
            if v not in dist:
                dist[v] = dist[u] + 1
                queue.append(v)

    case += 1
    print("Case {}: {}".format(case, ans))

