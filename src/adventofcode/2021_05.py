import sys
import math
from collections import Counter

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]

def sign(x):
    return 1 if x > 0 else -1 if x < 0 else 0


class Board:

    def __init__(self, A):
        self.A = A
        self.visited = [ [False for _ in range(len(A[0]))] for _ in range(len(A))]

    def place(self, num):
        A = self.A
        for i in range(len(A)):
            for j in range(len(A[0])):
                if A[i][j] == num:
                    self.visited[i][j] = True
                    return

    def win(self):
        visited = self.visited
        for i in range(len(visited)):
            if all(visited[i]):
                return True
        for j in range(len(visited[0])):
            if all(visited[i][j] for i in range(len(visited))):
                return True
        return False
    
    def unmarked_sum(self):
        ans = 0
        A, visited = self.A, self.visited
        for i in range(len(A)):
            for j in range(len(A[0])):
                if not visited[i][j]:
                    ans += A[i][j]
        return ans



def part1():
    data = [line.strip() for line in fin.read().strip().split("\n") if line.strip() != '']
    inf = 1 << 29
    m, M = inf, -inf


    lines = []
    counter = Counter()
    for each_data in data:
        begin, end = [s.strip() for s in each_data.strip().split("->")]
        x, y = [int(s) for s in begin.split(",")]
        xx, yy = [int(s) for s in end.split(",")]

        if x != xx and y != yy:
            continue

        for i in range(min(x, xx), max(x, xx) + 1):
            for j in range(min(y, yy), max(y, yy) + 1):
                counter[i, j] += 1

    print(sum(1 for v in counter.values() if v >= 2))
    


def part2():
    data = [line.strip() for line in fin.read().strip().split("\n") if line.strip() != '']
    inf = 1 << 29
    m, M = inf, -inf

    lines = []
    counter = Counter()
    for each_data in data:
        begin, end = [s.strip() for s in each_data.strip().split("->")]
        x, y = [int(s) for s in begin.split(",")]
        xx, yy = [int(s) for s in end.split(",")]

        dx = sign(xx - x)
        dy = sign(yy - y)

        # print(x, y, xx, yy, dx, dy)

        while x != xx or y != yy:
            counter[x, y] += 1
            x += dx
            y += dy
        counter[x, y] += 1


    print(sum(1 for v in counter.values() if v >= 2))



with open('in.txt', 'r') as fin:
    # part1()
    part2()
