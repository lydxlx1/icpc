package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1006 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = Reader.next();
        String b = Reader.next();
        int[][] f = new int[a.length() + 1][b.length() + 1];
        for (int i = a.length() - 1; i >= 0; i--)
            for (int j = b.length() - 1; j >= 0; j--) {
                f[i][j] = Math.max(f[i + 1][j], f[i][j + 1]);
                if (a.charAt(i) == b.charAt(j)) f[i][j] = f[i + 1][j + 1] + 1;
            }

        StringBuilder builder = new StringBuilder();
        int i = 0, j = 0;
        while (i < a.length() && j < b.length())
            if (a.charAt(i) == b.charAt(j)) {
                builder.append(a.charAt(i));
                i++;
                j++;
            } else if (f[i][j] == f[i + 1][j]) {
                i++;
            } else {
                j++;
            }
        System.out.println(builder.toString());

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
