package com._51nod;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class _1135 {

    static int pow(long a, long n, long mod) {
        long res = 1;
        while (n > 0) {
            if (n % 2 == 1)
                res = res * a % mod;
            a = a * a % mod;
            n /= 2;
        }
        return (int) res;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<Integer> factors = new HashSet<>();
        int P = Reader.nextInt();
        for (int i = 2; i * i <= P - 1; i++)
            if ((P - 1) % i == 0) {
                factors.add(i);
                factors.add((P - 1) / i);
            }

        for (int i = 2; i < P; i++) {
            boolean ok = true;
            for (int f : factors)
                if (pow(i, f, P) == 1) {
                    ok = false;
                    break;
                }

            if (ok) {
                System.out.println(i);
                break;
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
