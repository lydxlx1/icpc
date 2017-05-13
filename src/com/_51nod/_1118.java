package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1118 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int[][] choose = new int[n + m][];
        for (int i = 0; i < choose.length; i++) {
            choose[i] = new int[i + 1];
            choose[i][0] = choose[i][i] = 1;
            for (int j = 1; j < i; j++)
                choose[i][j] = (choose[i - 1][j] + choose[i - 1][j - 1]) % 1_000_000_007;
        }
        System.out.println(choose[n + m - 2][n - 1]);

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
