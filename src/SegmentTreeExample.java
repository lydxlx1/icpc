import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SegmentTreeExample {

    class SegmentTree {
        int begin;
        int end;
        int max;
        SegmentTree left, right;

        public SegmentTree(int begin, int end, int max) {
            this.begin = begin;
            this.end = end;
            this.max = max;
        }

        void grow() {
            if (begin < end && left == null) {
                int mid = (begin + end) / 2;
                left = new SegmentTree(begin, mid, max);
                right = new SegmentTree(mid + 1, end, max);
            }
        }

        void combine() {
            max = Math.max(left.max, right.max);
        }
    }

    void insert(SegmentTree tree, int x, int val) {
        tree.grow();
        if (tree.begin > x || tree.end < x) return;
        else if (tree.begin == x && tree.end == x) {
            tree.max = Math.max(tree.max, val);
        } else {
            insert(tree.left, x, val);
            insert(tree.right, x, val);
            tree.combine();
        }
    }

    int query(SegmentTree tree, int left, int right) {
        tree.grow();
        if (tree.begin > right || tree.end < left) return -1;
        else if (left <= tree.begin && tree.end <= right) {
            return tree.max;
        } else {
            int L = query(tree.left, left, right);
            int R = query(tree.right, left, right);
            tree.combine();
            return Math.max(L, R);
        }
    }

    SegmentTree root;


    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int inf = 1000000000;
        int dot = 0;
        int query = 1;
        int Q = queries.length, N = nums1.length;
        List<Integer>[] events = new List[Q + N];
        int[] ans = new int[Q];
        root = new SegmentTree(0, inf, -1);

        for (int i = 0; i < Q; i++) {
            events[i] = Arrays.asList(queries[i][0], queries[i][1], query, i);
        }
        for (int i = 0; i < N; i++) {
            events[i + Q] = Arrays.asList(nums1[i], nums2[i], dot, i);
        }
        Arrays.sort(events, Comparator.<List<Integer>>comparingInt(e -> e.get(0)).reversed()
                .thenComparing(Comparator.<List<Integer>>comparingInt(e -> e.get(1)).reversed())
                .thenComparingInt(e -> e.get(2))
                .thenComparingInt(e -> e.get(3))
        );
        for (List<Integer> e : events) {
            int x = e.get(0), y = e.get(1), type = e.get(2), idx = e.get(3);
            if (type == dot) {
                insert(root, y, x + y);
            } else {
                ans[idx] = query(root, y, inf);
            }
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        {
            SegmentTreeExample sol = new SegmentTreeExample();
            int[] nums1 = {4, 3, 1, 2};
            int[] nums = {2, 4, 9, 5};
            int[][] queries = {{4, 1}, {1, 3}, {2, 5}};
            int[] ans = sol.maximumSumQueries(nums1, nums, queries);
            System.out.println(Arrays.toString(ans));
        }
        {
            SegmentTreeExample sol = new SegmentTreeExample();
            int[] nums1 = {3, 2, 5};
            int[] nums = {2, 3, 4};
            int[][] queries = {{4, 4}, {3, 2}, {1, 1}};
            int[] ans = sol.maximumSumQueries(nums1, nums, queries);
            System.out.println(Arrays.toString(ans));
        }
        {
            SegmentTreeExample sol = new SegmentTreeExample();
            int[] nums1 = {2, 1};
            int[] nums = {2, 3};
            int[][] queries = {{3, 3}};
            int[] ans = sol.maximumSumQueries(nums1, nums, queries);
            System.out.println(Arrays.toString(ans));
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
