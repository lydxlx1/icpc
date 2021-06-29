package com.codeforces.edu.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TotalLength {

    static {
        Reader.init(System.in);
    }

    public static void main(String[] args) throws Exception {
        int n = Reader.nextInt();
        long T = Long.parseLong(Reader.next());

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Reader.nextInt();
        }

        long ans = 0;
        long s = 0;
        for (int i = 0, j = 0; j < n; j++) {
            s += a[j];
            while (s > T) {
                s -= a[i];
                i++;
            }
            long len = j - i + 1;
            ans += len * (len + 1) / 2;
        }

        System.out.println(ans);
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
