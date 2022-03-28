class Solution:
    def minDeletion(self, nums: List[int]) -> int:
        ans = 0
        A = []
        for i in nums:
            if len(A) % 2 == 1 and i == A[-1]:
                ans += 1
            else:
                A.append(i)
        if len(A) % 2 == 1:
            ans += 1
        return ans
