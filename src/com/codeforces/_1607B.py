"""Codeforces 1607B

"""
import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


T = eval(input())
for _ in range(T):
    x0, n = readln_int()

    def seq(n):
        """Compute sum of 1 - (2+3) + (4+5) - (6+7) + ...
        Let's break it down into the following parts
        1 + 
        -2 -6 -10 ...
        -3 -7 -11 ...
        +4 +8 +12 ...
        +5 +9 +13 ...
        """
        if n == 0: return 0

        def sum(a1, diff, T):
            """a1, a1 + diff, a1 + 2 * diff + ..., where the last term is <= T"""
            if a1 > T: return 0
            an = T
            while (an - a1) % diff != 0:
                an -= 1
            k = (an - a1) // diff + 1
            return (a1 + an) * k // 2

        return 1 - sum(2, 4, n) - sum(3, 4, n) + sum(4, 4, n) + sum(5, 4, n)

    ans = x0 - seq(n) if x0 % 2 == 0 else x0 + seq(n)
    print(ans)


