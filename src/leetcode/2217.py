class Solution:
    def kthPalindrome(self, queries: List[int], intLength: int) -> List[int]:
        how_many = [0] * 20
        how_many[0] = 1  # empty string
        how_many[1] = 10  # 0, 1, ..., 9
        for i in range(2, len(how_many)):
            how_many[i] = 10 * how_many[i - 2]

        def f(rank, length):
            ans = [0] * length
            i, j = 0, len(ans) - 1
            while i <= j:
                digit = 1 if i == 0 else 0
                bits_left = max((j - 1) - (i + 1) + 1, 0)
                while digit < 9 and rank > how_many[bits_left]:
                    digit += 1
                    rank -= how_many[bits_left]
                ans[i] = ans[j] = digit
                i += 1
                j -= 1

            if rank > 1:
                return -1
            return int("".join(str(i) for i in ans))

        ans = []
        for q in queries:
            ans.append(f(q, intLength))
        return ans
