package _51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1081 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Reader.nextInt();
            if (i > 0)
                a[i] += a[i - 1];
        }

        int q = Reader.nextInt();
        while (q-- > 0) {
            int i = Reader.nextInt() - 1;
            int j = i + Reader.nextInt() - 1;
            System.out.println(a[j] - (i - 1 >= 0 ? a[i - 1] : 0));
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
