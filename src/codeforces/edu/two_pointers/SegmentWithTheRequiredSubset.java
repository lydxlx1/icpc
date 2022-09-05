package codeforces.edu.two_pointers;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SegmentWithTheRequiredSubset {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        int s = Reader.nextInt();

        int[] f = new int[1111];
        Arrays.fill(f, -1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt();

            for (int j = s; j - x >= 0; j--) {
                f[j] = Math.max(f[j], f[j - x]);
            }

            // The order here is quite important, if doing DP inside only one array
            f[x] = i;

            if (f[s] != -1) {
                ans = Math.min(ans, i - f[s] + 1);
            }
        }

        System.out.println(ans < Integer.MAX_VALUE ? ans : -1);
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
