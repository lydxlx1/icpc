class Solution:
    def findDifference(self, nums1: List[int], nums2: List[int]) -> List[List[int]]:
        A, B = set(nums1), set(nums2)
        return [list(A - B), list(B - A)]

