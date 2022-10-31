import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static TreeNode get(Integer[] arr) {
        if (arr.length == 0) return null;
        int ptr = 1;
        TreeNode root = new TreeNode(arr[0]);
        List<TreeNode> cur = Arrays.asList(root);
        while (!cur.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : cur) {
                node.left = ptr < arr.length && arr[ptr] != null ? new TreeNode(arr[ptr]) : null;
                ptr++;
                node.right = ptr < arr.length && arr[ptr] != null ? new TreeNode(arr[ptr]) : null;
                ptr++;
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            cur = next;
        }
        return root;
    }

    @Override
    public String toString() {
        return String.format("%d, (%s), (%s)", val, left == null ? "" : left, right == null ? "" : right);
    }
}