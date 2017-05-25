package com.codeforces;

import java.io.*;
import java.util.*;

public class _809B {

    static int n, k;

    static boolean le(int a, int b) {
        System.out.println(1 + " " + a + " " + b);
        System.out.flush();
        String s = null;
        try {
            s = Reader.next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return s.equals("TAK");
        }
    }

    static boolean noWorseThanLeft(int mid) {
        return mid == 1 || le(mid, mid - 1);
    }

    static boolean noWorseThanRight(int mid) {
        return mid == n || le(mid, mid + 1);
    }


    static int step1() throws Exception {
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (noWorseThanRight(mid)) // [left, mid] are safe
                right = mid;
            else  // mid is definitely not ordered and [mid + 1, right] is safe
                left = mid + 1;
        }
        return left;
    }

    static int step2A(int left, int right) throws Exception {
        while (left < right) {
            int mid = (left + right) / 2; // floor
            if (noWorseThanRight(mid))
                right = mid;
            else
                left = mid + 1;
        }
        if (noWorseThanLeft(left) && noWorseThanRight(left))
            return left;
        else
            return -1;
    }

    static int step2B(int left, int right) throws Exception {
        while (left < right) {
            int mid = (left + right + 1) / 2; // ceiling
            if (noWorseThanLeft(mid))
                left = mid;
            else
                right = mid - 1;
        }
        return left; // guarantee to be ordered
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Reader.nextInt();
        k = Reader.nextInt();

        int one = step1();

        int two = -1;
        if (one > 1)
            two = step2A(1, one - 1);
        if (two == -1)
            two = step2B(one + 1, n);

        System.out.println(2 + " " + one + " " + two);
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
