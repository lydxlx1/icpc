package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1183 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = Reader.next();
        String b = Reader.next();
        int[][] f = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++)
            f[i][0] = i;
        for (int j = 0; j <= b.length(); j++)
            f[0][j] = j;

        for (int i = 1; i <= a.length(); i++)
            for (int j = 1; j <= b.length(); j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + 1;
                if (a.charAt(i - 1) == b.charAt(j - 1))
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1]);
                else
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + 1);
            }
        System.out.println(f[a.length()][b.length()]);

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
