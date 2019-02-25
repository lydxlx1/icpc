"""Codeforces 1131A
"""
import sys

w1, h1, w2, h2 = [int(i) for i in sys.stdin.readline().split(' ')]
print(2 * (w1 + h1 + h2) + 4)

