package leetcode;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class _2407_Treap {
    static public class Treap {
        private static int BUFFER_SIZE = 1234;
        private static Random rand = new Random();
        private static Treap[] smaller = new Treap[BUFFER_SIZE];
        private static Treap[] bigger = new Treap[BUFFER_SIZE];

        private int key;
        private int priority;
        private int size;

        private int val;
        private int maxValue;
        private Treap left, right;

        public Treap(int key, int val) {
            this.key = key;
            this.priority = rand.nextInt();
            this.size = 1;
            this.val = val;
            this.maxValue = val;
        }

        private void recompute() {
            this.size = 1 + (left != null ? left.size : 0) + (right != null ? right.size : 0);
            int lVal = left != null ? left.maxValue : Integer.MIN_VALUE;
            int rVal = right != null ? right.maxValue : Integer.MIN_VALUE;
            this.maxValue = Math.max(this.val, Math.max(lVal, rVal));
        }

        static Treap merge(Treap smaller, Treap bigger) {
            if (smaller == null)
                return bigger;
            if (bigger == null)
                return smaller;
            if (smaller.priority < bigger.priority) {
                smaller.right = merge(smaller.right, bigger);
                smaller.recompute();
                return smaller;
            } else {
                bigger.left = merge(smaller, bigger.left);
                bigger.recompute();
                return bigger;
            }
        }

        static Treap[] split(Treap root, int key) {
            int smallerSize = 0, biggerSize = 0;
            while (root != null)
                if (root.key < key) {
                    smaller[smallerSize++] = root;
                    root = root.right;
                } else {
                    bigger[biggerSize++] = root;
                    root = root.left;
                }
            smaller[smallerSize++] = null;
            bigger[biggerSize++] = null;
            for (int i = 0; i < smallerSize - 1; i++)
                smaller[i].right = smaller[i + 1];
            for (int i = 0; i < biggerSize - 1; i++)
                bigger[i].left = bigger[i + 1];
            for (int i = smallerSize - 2; i >= 0; i--)
                smaller[i].recompute();
            for (int i = biggerSize - 2; i >= 0; i--)
                bigger[i].recompute();
            return new Treap[]{smaller[0], bigger[0]};
        }

        static Treap insert(Treap root, int key, int val) {
            Treap[] subtrees = split(root, key);
            Treap a = subtrees[0];
            Treap b = subtrees[1];
            return merge(merge(a, new Treap(key, val)), b);
        }

        void inorder() {
            if (left != null) {
                left.inorder();
            }
            System.err.println(String.format("key=%d, val=%d, maxval = %d, size=%d", key, val, maxValue, size));
            if (right != null) {
                right.inorder();
            }
        }
    }

    public int lengthOfLIS(int[] nums, int k) {
        int ans = 1;
        Treap tree = new Treap(nums[0], 1);
        for (int i = 1; i < nums.length; i++) {
            int best = 1;

            Treap[] tmp = Treap.split(tree, nums[i] - k);
            Treap a = tmp[0];
            tmp = Treap.split(tmp[1], nums[i]);
            Treap b = tmp[0], c = tmp[1];

            if (b != null) {
                best = Math.max(best, 1 + b.maxValue);
            }

            tree = Treap.merge(Treap.merge(a, b), c);
            tree = Treap.insert(tree, nums[i], best);

            ans = Math.max(ans, best);
        }
        return ans;
    }


    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        _2407_Treap sol = new _2407_Treap();
        int[] input = IntStream.range(0, 100000).toArray();
        int k = 1;
        System.out.println(sol.lengthOfLIS(input, k));

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
