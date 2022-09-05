import sys
import math

def readln_str():
    return input().split(" ")

def readln_int():
    return [int(i) for i in readln_str()]

def part1():
    lines = fin.read().strip().split("\n")
    x, y = 0, 0
    for line in lines:
        op, num = line.split(" ")
        if op == "forward":
            x += int(num)
        elif op == "down":
            y += int(num)
        elif op == "up":
            y -= int(num)
        else:
            print("Error...")
    print(x, y, x * y)

def part2():
    lines = fin.read().strip().split("\n")
    H, D, aim = 0, 0, 0

    for line in lines:
        op, num = line.split(" ")
        if op == "forward":
            H += int(num)
            D += aim * int(num)
        elif op == "down":
            aim += int(num)
        elif op == "up":
            aim -= int(num)
        else:
            print("Error...")
    print(H, D, H * D)



with open('in.txt', 'r') as fin:
    # part1()
    part2()
