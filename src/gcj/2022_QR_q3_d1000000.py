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
    N, = readln_int()
    A = list(reversed(sorted(readln_int())))
    ans = 0
    while A:
        if A[-1] >= ans + 1:
            ans += 1
        A.pop()
    print(f"Case #{case + 1}: {ans}")






