"""
UVa 314 - Robot
"""
from typing import *

cin_buffer = []
cin_buffer_ptr = 0


def next() -> str:
    global cin_buffer
    global cin_buffer_ptr
    if cin_buffer_ptr >= len(cin_buffer):
        cin_buffer = input().split()
        cin_buffer_ptr = 0
        return next()
    else:
        res = cin_buffer[cin_buffer_ptr]
        cin_buffer_ptr += 1
        return res


while True:
    r, c = int(next()), int(next())
    if r == 0 and c == 0:
        break
    board = [[int(next()) for _ in range(c)] for _ in range(r)]

    start_x, start_y = int(next()) - 1, int(next()) - 1
    end_x, end_y = int(next()) - 1, int(next()) - 1

    dir = {"north": 0, "east": 1, "south": 2, "west": 3}[next()]

    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]


    def in_grid(x, y):
        return 0 <= x < r and 0 <= y < c


    def isok(x, y):
        return 0 <= x < r - 1 and 0 <= y < c - 1 \
               and board[x][y] == 0 and board[x][y + 1] == 0 and board[x + 1][y] == 0 and board[x + 1][y + 1] == 0


    dist = {}
    queue = Deque()
    if isok(start_x, start_y):
        dist[start_x, start_y, dir] = 0
        queue.append((start_x, start_y, dir))


    def try_enqueue(x, y, dir, d):
        """
        :return: whether the robot is not blocked, i.e., True = not blocked, False = blocked
        """
        if isok(x, y) and (x, y, dir) not in dist:
            dist[x, y, dir] = d
            queue.append((x, y, dir))  # It is wrong to return True here and return False in the else clause.
        return isok(x, y)


    ans = -1
    while queue:
        # (x, y) is the cell-coordinate of top-left cell of the robot.
        x, y, dir = queue.popleft()
        d = dist[x, y, dir]
        if x == end_x and y == end_y:
            ans = d
            break

        try_enqueue(x, y, (dir + 1) % 4, d + 1)
        try_enqueue(x, y, (dir + 3) % 4, d + 1)
        for step in range(1, 4):  # step in {1, 2, 3}
            if not try_enqueue(x + step * dx[dir], y + step * dy[dir], dir, d + 1):
                break  # We must stop if encounter a dead end

    print(ans)

