import random
import sys

T, A, B = [int(i) for i in input().strip().split()]
EPSILON = 2
INF = 10 ** 9

for case in range(1, T + 1):

    lucky = []

    while True:
        x = random.randint(-(10 ** 9), 10 ** 9)
        y = random.randint(-(10 ** 9), 10 ** 9)
        print(x, y)
        state = input()
        if state in {"CENTER", "HIT"}:
            if state == "CENTER":
                lucky.append(0)
            break
    if lucky: continue


    def dist(a, b):
        return ((a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2) ** 0.5


    def binary_search(out_disk, in_disk):
        print(*out_disk)
        cnt = 0
        while cnt < 40 and dist(in_disk, out_disk) > EPSILON:
            pre_in_dist, pre_out_dist = in_disk, out_disk

            cnt += 1
            (x, y), (xx, yy) = in_disk, out_disk
            mid = ((x + xx) // 2, (y + yy) // 2)
            print(*mid)
            state = input()
            if state in {"CENTER", "HIT"}:
                in_disk = mid
                if state == "CENTER":
                    lucky.append(0)
                    break
            else:
                out_disk = mid

            if in_disk == pre_in_dist and out_disk == pre_out_dist:
                break
        return (out_disk, in_disk)


    def center(A, B, C):
        (x1, y1), (x2, y2), (x3, y3) = A, B, C
        A = x1 * (y2 - y3) - y1 * (x2 - x3) + x2 * y3 - x3 * y2
        B = (x1 ** 2 + y1 ** 2) * (y3 - y2) + (x2 ** 2 + y2 ** 2) * (y1 - y3) + (x3 ** 2 + y3 ** 2) * (y2 - y1)
        C = (x1 ** 2 + y1 ** 2) * (x2 - x3) + (x2 ** 2 + y2 ** 2) * (x3 - x1) + (x3 ** 2 + y3 ** 2) * (x1 - x2)
        return (-B / A / 2, -C / A / 2)


    pairs = []
    pairs.append(binary_search((-INF, -INF), (x, y)))
    if lucky: continue
    pairs.append(binary_search((-INF, INF), (x, y)))
    if lucky: continue
    pairs.append(binary_search((INF, INF), (x, y)))
    if lucky: continue

    x, xx, y, yy = None, None, None, None
    for A in pairs[0]:
        for B in pairs[1]:
            for C in pairs[2]:
                i, j = center(A, B, C)
                if x is None:
                    x, y, xx, yy = i, j, i, j
                x = min(x, i)
                y = min(y, j)
                xx = max(xx, i)
                yy = max(yy, j)


    def gogogo():
        for i in range(int(x) - 1, int(xx) + 2):
            for j in range(int(y) - 1, int(yy) + 2):
                print(i, j)
                state = input()
                if state in {"CENTER"}:
                    return


    gogogo()
