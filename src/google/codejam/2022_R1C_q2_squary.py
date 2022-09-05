import sys
import math
import resource, sys

sys.setrecursionlimit(10 ** 6)
INF = 1 << 100
CIN_BUFFER = []


def next_token():
    global CIN_BUFFER
    while len(CIN_BUFFER) == 0:
        CIN_BUFFER = [s.strip() for s in input().split(" ") if len(s.strip()) > 0]
    return CIN_BUFFER.pop()


def readln_str():
    return input().split(" ")


def readln_int():
    return [int(i) for i in readln_str()]


T, = readln_int()
for case in range(T):
    N, K = readln_int()
    A = readln_int()

    s = sum(A)
    ss = sum(i * i for i in A)

    if K == 1:
        if s == 0:
            if ss == 0:
                ans = "0"
            else:
                ans = "IMPOSSIBLE"
        else:
            rhs = ss - s * s
            if rhs % (2 * s) == 0:
                ans = rhs // (2 * s)
            else:
                ans = "IMPOSSIBLE"
    else:
        cb = 1 - s
        wb = (s * s - 2 * s + ss) // 2
        ans = f"{cb} {wb}"
        assert sum(A + [cb, wb]) ** 2 == sum(i * i for i in (A + [cb, wb]))

    print(f"Case #{case + 1}: {ans}")
