package com.codeforces;

import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class _1367B {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Reader.nextInt();
        while (T-- > 0) {
            int N = Reader.nextInt();
            int[] cnt = {0, 0};
            for (int i = 0; i < N; i++) {
                if (Reader.nextInt() % 2 != i % 2) {
                    cnt[i % 2]++;
                }
            }
            if (cnt[0] == cnt[1]) {
                System.out.println(cnt[0]);
            } else {
                System.out.println(-1);
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
