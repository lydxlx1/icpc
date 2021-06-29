package com.codeforces.edu.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CardSubstrings {

    static {
        Reader.init(System.in);
    }

    public static void main(String[] args) throws Exception {
        int n = Reader.nextInt();
        int m = Reader.nextInt();
        String S = Reader.next();
        String card = Reader.next();

        int[] cnt = new int[128];
        for (int i = 0; i < card.length(); i++) {
            cnt[card.charAt(i)]++;
        }
        long ans = 0;
        for (int end = 0, start = 0; end < S.length(); end++) {
            cnt[S.charAt(end)]--;
            while (cnt[S.charAt(end)] < 0) {
                cnt[S.charAt(start)]++;
                start++;
            }
            ans += end - start + 1;
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
