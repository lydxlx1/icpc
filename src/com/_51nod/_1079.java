package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1079 {

    static long[] extendedGcd(long a, long b) {
        if (b == 0)
            return new long[]{a, 1, 0};
        else {
            long[] next = extendedGcd(b, a % b);
            long g = next[0];
            long x = next[2];
            long y = next[1] - next[2] * (a / b);
            return new long[]{g, x, y};
        }
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        long[] p = new long[n];
        long[] r = new long[n];
        long m = 1;
        for (int i = 0; i < n; i++) {
            p[i] = Reader.nextInt();
            r[i] = Reader.nextInt();
            m = m * p[i];
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            long prod = 1;
            for (int j = 0; j < n; j++)
                if (i != j) {
                    prod = prod * p[j] % m;
                    long inv = extendedGcd(p[j], p[i])[1]; // inv(p[j]) modulo p[i]
                    inv = (inv % p[i] + p[i]) % p[i]; // make inv non-negative
                    prod = prod * inv % m;
                }
            res = (res + prod * r[i]) % m;
        }
        System.out.println(res);

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
