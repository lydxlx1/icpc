import sys
import math

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
    A = []
    A.append(readln_int())
    A.append(readln_int())
    A.append(readln_int())

    M = [1 << 30] * 4
    for row in A:
        for j in range(4):
            M[j] = min(M[j], row[j])
    if sum(M) < 1000000:
        ans = "IMPOSSIBLE"
    else:
        target = 1000000
        for i in range(4):
            tmp = min(M[i], target)
            M[i] = tmp
            target -= tmp
        ans = " ".join(str(i) for i in M)

    print(f"Case #{case + 1}: {ans}")






