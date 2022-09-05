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
    R, C = readln_int()
    board = [['.' for _ in range(2 * C + 1)] for _ in range(2 * R + 1)]

    for i in range(0, len(board), 2):
        for j in range(0, len(board[i]), 2):
            board[i][j] = '+'
            if i + 1 < len(board):
                board[i+1][j] = '|'
            if j + 1 < len(board[i]):
                board[i][j+1] = '-'

    board[0][0] = board[0][1] = board[1][0] = board[1][1] = '.'

    print(f"Case #{case + 1}:")
    for row in board:
        print(''.join(row))






