package codeforces.edu.two_pointers;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ABKnapsack {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int s = Reader.nextInt();
        int A = Reader.nextInt();
        int B = Reader.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = Reader.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = Reader.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        long ans = 0;
        long cur = 0;
        int i = n - 1, j = m - 1;

        // First take as many items of type B as possible
        while (j >= 0 && s - B >= 0) {
            s -= B;
            cur += b[j--];
            ans = Math.max(ans, cur);
        }
        j++;

        // Try to take items in A one at a time.
        // When the knapsack is overflow, pop some items in B with the current minimum costs.
        while (i >= 0) {
            s -= A;
            cur += a[i--];
            while (s < 0 && j < m) {
                s += B;
                cur -= b[j++];
            }
            if (s < 0) {
                break;
            }
            ans = Math.max(ans, cur);
        }


        System.out.println(ans);
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
