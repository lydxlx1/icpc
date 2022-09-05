"""
Chinese Remainder Theorem
"""
bases = [5, 7, 11, 13, 17, 18]
lcm = 1531530
ans = {}
for i in range(lcm):
    mods = tuple(i % f for f in bases)
    ans[mods] = i

T, N, M = [int(i.strip()) for i in input().split(' ')]
for _case in range(T):
    mods = []
    for f in bases:
        print(*([str(f)] * 18))
        mods.append(sum([int(i.strip()) for i in input().split(' ')]) % f)
    print(ans[tuple(mods)])
    input()
