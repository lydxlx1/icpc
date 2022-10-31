package leetcode;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2458_Height_of_Binary_Tree_After_Subtree_Removal_Queries {

    /*
     * report the max element of an interval
     * can added a constant to an interval
     */
    static class SegmentTree {
        private int[] input;
        private int[] begin;
        private int[] end;
        private int[] max;
        private int[] mask;
        private int left, right, delta;

        public SegmentTree(int[] input) {
            this.input = input;
            begin = new int[4 * input.length];
            end = new int[4 * input.length];
            max = new int[4 * input.length];
            mask = new int[4 * input.length];
            build(1, 0, input.length - 1);
        }

        private void build(int i, int left, int right) {
            begin[i] = left;
            end[i] = right;
            if (left == right) max[i] = input[left];
            else {
                int mid = left + (right - left) / 2;
                build(2 * i, left, mid);
                build(2 * i + 1, mid + 1, right);
                max[i] = Math.max(max[2 * i], max[2 * i + 1]);
            }
        }

        private void push(int i) {
            max[i] += mask[i];
            if (begin[i] < end[i]) {
                mask[2 * i] += mask[i];
                mask[2 * i + 1] += mask[i];
            }
            mask[i] = 0;
        }

        public void update(int left, int right, int delta) {
            this.left = left;
            this.right = right;
            this.delta = delta;
            update(1);
        }

        private void update(int i) {
            push(i);
            if (right < begin[i] || end[i] < left) return;
            else if (left <= begin[i] && end[i] <= right) {
                mask[i] += delta;
                push(i);
            } else {
                update(2 * i);
                update(2 * i + 1);
                max[i] = Math.max(max[2 * i], max[2 * i + 1]);
            }
        }

        public int query(int left, int right) {
            this.left = left;
            this.right = right;
            return query(1);
        }

        private int query(int i) {
            push(i);
            if (right < begin[i] || end[i] < left) return Integer.MIN_VALUE;
            else if (left <= begin[i] && end[i] <= right) return max[i];
            else return Math.max(query(2 * i), query(2 * i + 1));
        }
    }

    int MAX = 100005;
    int ts;
    int[] begin, end;
    int[] depthPerTs;

    void dfs(TreeNode root, int d) {
        if (root == null) return;

        begin[root.val] = ts;
        depthPerTs[ts] = d;
        ts++;

        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
        end[root.val] = ts;
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        begin = new int[MAX];
        end = new int[MAX];
        depthPerTs = new int[MAX];

        dfs(root, 0);
        SegmentTree tree = new SegmentTree(Arrays.copyOf(depthPerTs, ts));
        int[] ans = new int[queries.length];
        int INF = 1 << 29;
        for (int i = 0; i < ans.length; i++) {
            int L = begin[queries[i]];
            int R = end[queries[i]] - 1;

            tree.update(L, R, -INF);
            ans[i] = tree.query(0, ts);
            tree.update(L, R, INF);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        {
            var tree = TreeNode.get(new Integer[]{1, 3, 4, 2, null, 6, 5, null, null, null, null, null, 7});
            System.out.println(tree);
            var queries = new int[]{4};
            System.out.println(Arrays.toString(new _2458_Height_of_Binary_Tree_After_Subtree_Removal_Queries().treeQueries(tree, queries)));
        }
        {
            var tree = TreeNode.get(new Integer[]{5, 8, 9, 2, 1, 3, 7, 4, 6});
            System.out.println(tree);
            var queries = new int[]{3, 2, 4, 8};
            System.out.println(Arrays.toString(new _2458_Height_of_Binary_Tree_After_Subtree_Removal_Queries().treeQueries(tree, queries)));
        }

        cout.close();
    }


    /**
     * Class for buffered reading int and double values
     */
    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /**
         * call this method to initialize reader for InputStream
         */
        static void init(InputStream input) {
            reader = new BufferedReader(new InputStreamReader(input));
            tokenizer = new StringTokenizer("");
        }

        /**
         * get next word
         */
        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                // TODO add check for eof if necessary
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
