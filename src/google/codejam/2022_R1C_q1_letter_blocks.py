import string
import sys
import math
import resource, sys

sys.setrecursionlimit(10 ** 8)
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
    N, = readln_int()
    A = readln_str()


    def doit():
        # Handle unique strings
        for i in range(len(A)):
            if len(set(A[i])) == 1:
                for j in range(len(A)):
                    if i != j and A[i][0] == A[j][0]:
                        remove1, remove2, add1 = A[i], A[j], A[i] + A[j]
                        A.remove(remove1), A.remove(remove2), A.append(add1)
                        return doit()
                    if i != j and A[i][0] == A[j][-1]:
                        remove1, remove2, add1 = A[i], A[j], A[j] + A[i]
                        A.remove(remove1), A.remove(remove2), A.append(add1)
                        return doit()
                # Didn't fetch a string with first / last character matching
                remove = A[i]
                A.remove(remove)
                return remove + doit()

        for i in range(len(A)):
            for j in range(len(A)):
                if i != j and A[i][-1] == A[j][0]:
                    remove1, remove2, add1 = A[i], A[j], A[i] + A[j]
                    A.remove(remove1), A.remove(remove2), A.append(add1)
                    return doit()

        return "".join(A)


    ans = doit()
    for ch in string.ascii_uppercase:
        indices = [i for i in range(len(ans)) if ans[i] == ch]
        if any(True for i in range(len(indices) - 1) if indices[i] + 1 < indices[i + 1]):
            ans = "IMPOSSIBLE"

    print(f"Case #{case + 1}: {ans}")
