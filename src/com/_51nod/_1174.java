package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1174 {

    static int[] a, max;
    static int n;

    static void build(int i, int l, int r) {
        if (l == r) {
            max[i] = a[l];
        } else {
            int mid = (l + r) / 2;
            build(2 * i, l, mid);
            build(2 * i + 1, mid + 1, r);
            max[i] = Math.max(max[2 * i], max[2 * i + 1]);
        }
    }

    static int query(int i, int begin, int end, int l, int r) {
        if (begin > r || end < l) return Integer.MIN_VALUE;
        if (l <= begin && end <= r) return max[i];
        int mid = (begin + end) / 2;
        return Math.max(query(2 * i, begin, mid, l, r), query(2 * i + 1, mid + 1, end, l, r));
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Reader.nextInt();
        a = new int[n];
        max = new int[4 * n];
        for (int i = 0; i < n; i++)
            a[i] = Reader.nextInt();
        build(1, 0, n - 1);

        int q = Reader.nextInt();
        while (q-- > 0) {
            int l = Reader.nextInt();
            int r = Reader.nextInt();
            System.out.println(query(1, 0, n - 1, l, r));
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
