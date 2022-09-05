package codeforces;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Try to optimize the O(n^2)-time DP solution via segment tree, but failed...
 */
public class _809D_TLE_2 {

    /**
     * Always a full binary tree
     */
    static class Node {
        int min;
        int max;
        int additionTag;
        int size;
        Node left;
        Node right;

        public Node(int val) {
            this.min = this.max = val;
            this.size = 1;
        }

        public void push() {
            min += additionTag;
            max += additionTag;
            if (!isLeaf()) {
                left.additionTag += additionTag;
                right.additionTag += additionTag;
            }
            additionTag = 0;
        }

        public void combine() {
            if (!isLeaf()) {
                min = Math.min(left.min, right.min);
                max = Math.max(left.max, right.max);
                size = left.size + right.size;
            }
        }

        public boolean isOnePiece() {
            return max - min + 1 == size;
        }

        public boolean isLeaf() {
            return left == null;
        }
    }

    final static int INF = 1 << 30;

    static Node build(int left, int right, int[] a) {
        if (left == right)
            return new Node(a[left]);
        else {
            Node node = new Node(-1);
            int mid = (left + right) / 2;
            node.left = build(left, mid, a);
            node.right = build(mid + 1, right, a);
            node.combine();
            return node;
        }
    }

    static int dfs(Node root) {
        root.push();
        int ans = 0;
        if (root.isLeaf()) {
//            System.out.println(root.min);
            if (root.min != INF)
                ans = 1;
        } else {
            ans = dfs(root.left) + dfs(root.right);
        }
        root.combine();
        return ans;
    }

    static long getBestValue(long previous, long current, int begin, int end) {
        long x = previous + 1, y = current;
        x = Math.max(x, begin);
        y = Math.min(y, end);
        return x <= y ? x : current;
    }

    static void doit(Node root, int begin, int end, long[] pred) {
        root.push();

        long potentialOldPred = -1;

        if (root.isLeaf()) {
            potentialOldPred = root.max; // get the predecessor before updating
            root.additionTag += getBestValue(pred[0], root.min, begin, end) - root.min;
            root.push();
        } else if (root.min > end) {
            doit(root.left, begin, end, pred); // terminating, no need to update pred
        } else if (root.max <= begin) {
            // do nothing here
        } else {
            if (root.isOnePiece()) {
                if (pred[0] + 1 != root.min)
                    doit(root.left, begin, end, pred);
            } else {
                doit(root.left, begin, end, pred);
                doit(root.right, begin, end, pred);
            }
        }

        pred[0] = potentialOldPred != -1 ? potentialOldPred : root.max;
        root.combine();
    }


    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        int[] a = new int[n + 10];
        Arrays.fill(a, INF);
        Node root = build(0, a.length - 1, a);
        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt();
            int y = Reader.nextInt();
            doit(root, x, y, new long[]{0});
//            dfs(root);
//            System.out.println();
        }

        System.out.println(dfs(root));
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
