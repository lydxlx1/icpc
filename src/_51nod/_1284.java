package _51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1284 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(Reader.next());
        int[] factors = {2, 3, 5, 7};
        long ans = 0;
        for (int mask = 0; mask < (1 << factors.length); mask++) {
            long prod = 1;
            for (int i = 0; i < factors.length; i++)
                if ((mask & (1 << i)) != 0) {
                    prod = prod * factors[i];
                }
            ans += N / prod * (Integer.bitCount(mask) % 2 == 0 ? 1 : -1);
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
