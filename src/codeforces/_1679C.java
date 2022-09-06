package codeforces;

import java.io.*;
import java.util.StringTokenizer;

public class _1679C {

    static class BinaryIndexedTreeInt {

        int bit[];

        /**
         * @param capacity - Note that index starts from 1.
         */
        public BinaryIndexedTreeInt(int capacity) {
            this.bit = new int[capacity + 10];
        }

        static int lowbit(int x) {
            return x & -x;
        }

        /**
         * @param x
         * @param val Performs the operation: bit[x] += val
         */
        void add(int x, int val) {
            for (; x < bit.length; x += lowbit(x)) {
                bit[x] += val;
            }
        }

        /**
         * @param x
         * @return bit[1] + bit[2] + ... + bit[x]
         */
        int sum(int x) {
            int sum = 0;
            for (; x > 0; x -= lowbit(x)) {
                sum += bit[x];
            }
            return sum;
        }
    }


    static boolean allInRange(BinaryIndexedTreeInt bit, int x1, int x2) {
        return bit.sum(x2) - bit.sum(x1 - 1) == x2 - x1 + 1;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Reader.nextInt();
        int Q = Reader.nextInt();
        BinaryIndexedTreeInt bitX = new BinaryIndexedTreeInt(N);
        BinaryIndexedTreeInt bitY = new BinaryIndexedTreeInt(N);
        int[] cntX = new int[N + 100];
        int[] cntY = new int[N + 100];
        for (int t = 0; t < Q; t++) {
            int type = Reader.nextInt();
            if (type == 1) {
                int x = Reader.nextInt();
                int y = Reader.nextInt();
                if (cntX[x]++ == 0)
                    bitX.add(x, 1);
                if (cntY[y]++ == 0)
                    bitY.add(y, 1);
            } else if (type == 2) {
                int x = Reader.nextInt();
                int y = Reader.nextInt();
                if (--cntX[x] == 0)
                    bitX.add(x, -1);
                if (--cntY[y] == 0)
                    bitY.add(y, -1);
            } else if (type == 3) {
                int x1 = Reader.nextInt();
                int y1 = Reader.nextInt();
                int x2 = Reader.nextInt();
                int y2 = Reader.nextInt();
                boolean hit = allInRange(bitX, x1, x2) || allInRange(bitY, y1, y2);
                cout.write(hit ? "Yes\n" : "No\n");
            } else {
                throw new RuntimeException("");
            }
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
