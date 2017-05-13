package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1126 {

    static final int MOD = 7;
    static final int M = 2;

    static int[][] mul(int[][] a, int[][] b) {
        int[][] c = new int[M][M];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < M; j++)
                for (int k = 0; k < M; k++)
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j] % MOD) % MOD;
        return c;
    }

    static int[][] pow(int[][] a, int n) {
        int[][] res = new int[M][M];
        for (int i = 0; i < M; i++)
            res[i][i] = 1;

        while (n > 0) {
            if (n % 2 == 1)
                res = mul(res, a);
            a = mul(a, a);
            n /= 2;
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Reader.nextInt();
        int b = Reader.nextInt();
        a = (a % MOD + MOD) % MOD;
        b = (b % MOD + MOD) % MOD;
        int n = Reader.nextInt();
        int[][] A = {
                {a, b},
                {1, 0},
        };

        if (n <= 2)
            System.out.println(1);
        else {
            A = pow(A, n - 2);
            int ans = (A[0][0] + A[0][1]) % MOD;
            System.out.println(ans);
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
