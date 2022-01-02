import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]


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
    lines = [line.strip() for line in fin.read().strip().split("\n") if line.strip() != '']
    seq = [int(token) for token in lines[0].split(",")]
    puzzles = []
    i = 1
    while i < len(lines):
        A = []
        for _ in range(5):
            row = [int(token) for token in lines[i].split(" ") if token.strip() != ""]
            A.append(row)
            i += 1
        puzzles.append(Board(A))

    def doit():
        for i in seq:
            for puzzle in puzzles:
                puzzle.place(i)
                if puzzle.win():
                    return i * puzzle.unmarked_sum()
        return None

    print(doit())


def part2():
    lines = [line.strip() for line in fin.read().strip().split("\n") if line.strip() != '']
    seq = [int(token) for token in lines[0].split(",")]
    puzzles = []
    i = 1
    while i < len(lines):
        A = []
        for _ in range(5):
            row = [int(token) for token in lines[i].split(" ") if token.strip() != ""]
            A.append(row)
            i += 1
        puzzles.append(Board(A))

    ans = None
    for i in seq:
        for puzzle in puzzles:
            if not puzzle.win():
                puzzle.place(i)
                if puzzle.win():
                    ans = i * puzzle.unmarked_sum()
    print(ans)



with open('in.txt', 'r') as fin:
    # part1()
    part2()
