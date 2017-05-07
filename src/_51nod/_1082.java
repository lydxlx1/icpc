package _51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1082 {

    static boolean irrelevant7(long x) {
        if (x % 7 == 0)
            return false;
        while (x > 0) {
            if (x % 10 == 7)
                return false;
            x /= 10;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] f = new long[1000001];
        for (int i = 1; i < f.length; i++) {
            f[i] = f[i - 1];
            if (irrelevant7(i))
                f[i] = f[i] + (long) i * i;
        }

        int T = Reader.nextInt();
        while (T-- > 0) {
            System.out.println(f[Reader.nextInt()]);
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
