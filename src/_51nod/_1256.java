package _51nod;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1256 {

    /**
     * Assume a >= b
     */
    static int[] gcd(int a, int b) {
        if (b == 0)
            return new int[]{a, 1, 0};
        else {
            int[] next = gcd(b, a % b);
            int g = next[0];
            int x = next[2];
            int y = next[1] - next[2] * (a / b);
            return new int[]{g, x, y};
        }
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Reader.nextInt();
        int n = Reader.nextInt();
        int[] g = gcd(n, m); // n > m
        int inv = (g[2] % n + n) % n;
        System.out.println(inv);

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
