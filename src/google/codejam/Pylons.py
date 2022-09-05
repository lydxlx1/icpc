def is_valid(i, j, ii, jj):
    return i != ii and j != jj and i - j != ii - jj and i + j != ii + jj


t = int(input().strip())
for _case in range(t):
    r, c = [int(i) for i in input().split(" ")]

    queue = []
    degree = {}
    next = None
    d = 1 << 29
    for i in range(r):
        for j in range(c):
            degree[(i, j)] = 0
            for ii in range(r):
                for jj in range(c):
                    if is_valid(i, j, ii, jj):
                        degree[(i, j)] += 1

            if degree[i, j] < d:
                d = degree[i, j]
                next = i, j

    visited = set()
    visited.add(next)
    for ii in range(r):
        for jj in range(c):
            if is_valid(ii, jj, next[0], next[1]):
                degree[ii, jj] -= 1

    ans = [next]
    while True:
        next = None
        d = 1 << 29
        i, j = ans[-1]
        for ii in range(r):
            for jj in range(c):
                if (ii, jj) not in visited and is_valid(i, j, ii, jj):
                    if degree[ii, jj] < d:
                        next = ii, jj
                        d = degree[ii, jj]

        if next:
            ans.append(next)
            visited.add(next)
            i, j = next
            for ii in range(r):
                for jj in range(c):
                    if is_valid(i, j, ii, jj):
                        degree[ii, jj] -= 1
        else:
            break

    if len(ans) < r * c:
        print('Case #{}: IMPOSSIBLE'.format(_case + 1))
    else:
        print('Case #{}: POSSIBLE'.format(_case + 1))
        for i, j in ans:
            print(i + 1, j + 1)
