package leetcode;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

public class _2276_CountIntervals {

    static public class Treap {
        private static int BUFFER_SIZE = 1234;
        private static Random rand = new Random();
        private static Treap[] smaller = new Treap[BUFFER_SIZE];
        private static Treap[] bigger = new Treap[BUFFER_SIZE];

        private int key;
        private int priority;
        private int size;

        private int val;
        private int total;
        private Treap left, right;

        private Treap leftmost, rightmost;

        public Treap(int key, int val) {
            this.key = key;
            this.priority = rand.nextInt();
            this.size = 1;
            this.val = val;
            this.total = this.val - this.key + 1;
            this.leftmost = this.rightmost = this;
        }

        private void recompute() {
            this.size = 1 + (left != null ? left.size : 0) + (right != null ? right.size : 0);
            int lVal = left != null ? left.total : 0;
            int rVal = right != null ? right.total : 0;
            this.total = (this.val - this.key + 1) + lVal + rVal;
            this.leftmost = this.left != null ? left.leftmost : this;
            this.rightmost = this.right != null ? right.rightmost : this;
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

//        void inorder() {
//            if (left != null) {
//                left.inorder();
//            }
//            System.err.println(String.format("key=%d, val=%d, maxval = %d, size=%d", key, val, maxValue, size));
//            if (right != null) {
//                right.inorder();
//            }
//        }
    }

    Treap tree;

    public _2276_CountIntervals() {
        tree = new Treap(-1, -1);
        tree = Treap.insert(tree, Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
    }

    public void add(int left, int right) {
        while (true) {
            Treap[] tmp = Treap.split(tree, right + 1); // key inside (-inf, right]
            int x = tmp[0].rightmost.key, y = tmp[0].rightmost.val;
            // Not intersect, note that this check needs to be compatible with Line 108
            if (x > right || y < left) {
                tree = Treap.merge(tmp[0], tmp[1]);
                break;
            } else {
                Treap b = tmp[1];
                tmp = Treap.split(tmp[0], x);
                Treap a = tmp[0];
                if (!(x == tmp[1].key && y == tmp[1].val)) {
                    throw new RuntimeException(x + " " + y + " " + tmp[1].key + " " + tmp[1].val);
                }
                left = Math.min(left, x);
                right = Math.max(right, y);
                tree = Treap.merge(a, b);
            }
        }
        tree = Treap.insert(tree, left, right);
    }

    public int count() {
        return tree.total - 2;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        _2276_CountIntervals sol = new _2276_CountIntervals();
        System.out.println(sol.count());
        sol.add(2, 3);
        System.out.println(sol.count());
        sol.add(7, 10);
        System.out.println(sol.count());
        sol.add(5, 8);
        System.out.println(sol.count());
        sol.add(1, 10);
        System.out.println(sol.count());
        System.out.println(sol.tree.size);


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
