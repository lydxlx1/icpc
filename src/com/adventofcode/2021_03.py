import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]

def part1():
    lines = fin.read().strip().split("\n")
    R, C = len(lines), len(lines[0])
    print(R, C)

    gamma, epsilon = 0, 0
    for j in range(C):
        one, zero = 0, 0
        for i in range(R):
            if lines[i][j] == '0':
                zero += 1
            else:
                one += 1
        gamma *= 2
        epsilon *= 2
        if zero > one:
            epsilon += 1
        else:
            gamma += 1

    print(gamma, epsilon, gamma * epsilon)


def part2():
    lines = fin.read().strip().split("\n")
    R, C = len(lines), len(lines[0])

    A = list(lines)
    j = 0
    while len(A) > 1:
        zero = [num for num in A if num[j] == '0']
        one = [num for num in A if num[j] == '1']
        if len(one) >= len(zero):
            A = one
        else:
            A = zero
        j += 1

    B = list(lines)
    j = 0
    while len(B) > 1:
        zero = [num for num in B if num[j] == '0']
        one = [num for num in B if num[j] == '1']
        if len(zero) <= len(one):
            B = zero
        else:
            B = one
        j += 1

    print(A, B)
    cb, wb = int(A[0], base=2), int(B[0], base=2)
    print(cb, wb, cb * wb)


with open('in.txt', 'r') as fin:
    # part1()
    part2()
