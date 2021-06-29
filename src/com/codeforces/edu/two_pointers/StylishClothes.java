package com.codeforces.edu.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class StylishClothes {

    static {
        Reader.init(System.in);
    }

    public static void main(String[] args) throws Exception {
        TreeSet<Integer> set = new TreeSet<>();

        int[][] A = new int[4][];

        for (int i = 0; i < 4; i++) {
            A[i] = new int[Reader.nextInt()];
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = Reader.nextInt();
                set.add(A[i][j]);
            }
            Arrays.sort(A[i]);
        }

        int cost = Integer.MAX_VALUE;
        int[] ans = new int[4];

        int[] ptr = {0, 0, 0, 0};
        for (int min : set) {
            boolean bad = false;
            for (int i = 0; i < ptr.length; i++) {
                while (ptr[i] < A[i].length && A[i][ptr[i]] < min) {
                    ptr[i]++;
                }
                if (ptr[i] >= A[i].length) {
                    bad = true;
                    break;
                }
            }

            if (bad) break;

            int m = IntStream.range(0, 4).map(i -> A[i][ptr[i]]).min().getAsInt();
            int M = IntStream.range(0, 4).map(i -> A[i][ptr[i]]).max().getAsInt();
            if (M - m < cost) {
                cost = M - m;
                ans = IntStream.range(0, 4).map(i -> A[i][ptr[i]]).toArray();
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2] + " " + ans[3]);
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
