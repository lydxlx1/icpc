package leetcode;

/**
 * LeetCode 951 - Flip Equivalent Binary Trees
 *
 * DFS
 * This problem will be harder and more interesting if duplicates are allowed in the tree.
 */
public class _951 {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2 == null;
        }
        if (root2 == null) {
            return root1 == null;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);

    }
}

