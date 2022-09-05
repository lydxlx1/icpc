"""
2218. Maximum Value of K Coins From Piles

Interesting knapsack problem, where each pile can be treated as 
a group of items that you can take at most one from them.
"""
class Solution:
    def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
        n = len(piles)
        pre = [0] * (k + 1)
        for pile in piles:
            cur = list(pre)
            weight, value = 0, 0
            for p in pile:
                weight += 1
                value += p

                j = k
                while j - weight >= 0:
                    cur[j] = max(cur[j], pre[j - weight] + value)
                    j -= 1
            pre = cur

        return pre[k]
