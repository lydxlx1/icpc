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
    s = input().strip()
    memo = {}

    def f(s):
        if s == "": return s
        if s in memo: return memo[s]
        sub = s[1:]
        ans = min(s[0] + f(sub), s[0] + s[0] + f(sub))
        memo[s] = ans
        return ans

    ans = f(s)
    print(f"Case #{case + 1}: {ans}")






