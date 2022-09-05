"""
UVa 383 - Shipping Routes
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


T = int(next())
print("SHIPPING ROUTES OUTPUT")
print()
for case in range(1, T + 1):
    n, m, q = int(next()), int(next()), int(next())
    nodes = [next() for _ in range(n)]
    inf = 1 << 29
    f = {}
    for u in nodes:
        f[u] = {}
        for v in nodes:
            f[u][v] = 0 if u == v else inf
    for _ in range(m):
        u, v = next(), next()
        f[u][v] = f[v][u] = 1
    # Floyd
    for k in nodes:
        for i in nodes:
            for j in nodes:
                f[i][j] = min(f[i][j], f[i][k] + f[k][j])

    print("DATA SET  {}".format(case))
    print("")
    for _ in range(q):
        weight, start, end = int(next()), next(), next()
        if f[start][end] < inf:
            print("${}".format(100 * weight * f[start][end]))
        else:
            print("NO SHIPMENT POSSIBLE")
    print("")

print("END OF OUTPUT")

