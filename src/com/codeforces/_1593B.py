"""Codeforces 1593B
"""
import sys


T = eval(input())
for _ in range(T):
    n = eval(input())

    def suffix_subsequence(n, target):
        cnt = 0
        while n > 0 and target > 1:  # target > 1 is to handle special case 100...
            if n % 10 == target % 10:
                target //= 10
                n //= 10
            else:
                n //= 10
                cnt += 1
        # print(n, target, cnt)
        return cnt if target <= 1 else 1 << 29

    print(min(suffix_subsequence(n, 100), suffix_subsequence(n, 25), suffix_subsequence(n, 50), suffix_subsequence(n, 75)))

