import sys
import math

INF = 1 << 50
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
    N, = readln_int()
    F = [0] + readln_int()
    edges = [[] for _ in range(N + 1)]
    P = readln_int()
    for i in range(N):
        # (i + 1) -> P[i]
        # And we want to reverse the edge
        # print(f"{P[i]} -> {i + 1}")
        edges[P[i]].append(i + 1)
    
    ans = 0
    def dfs(u, parent):
        global ans

        smallest = INF
        for v in edges[u]:
            if v == parent: continue
            tmp = dfs(v, u)
            ans += tmp
            smallest = min(smallest, tmp)
        if smallest == INF:
            ret = F[u]
        else:
            ans -= smallest
            ret = max(F[u], smallest)
        # print(u, parent, ret, ans)
        return ret

    tmp = dfs(0, -1)
    # print(tmp)
    ans += tmp
    print(f"Case #{case + 1}: {ans}")






