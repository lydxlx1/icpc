package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1085 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Reader.nextInt();
        int W = Reader.nextInt();
        int[] f = new int[W + 1];
        for (int i = 0; i < N; i++) {
            int w = Reader.nextInt();
            int p = Reader.nextInt();
            for (int j = W; j - w >= 0; j--)
                f[j] = Math.max(f[j], f[j - w] + p);
        }
        System.out.println(f[W]);

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
