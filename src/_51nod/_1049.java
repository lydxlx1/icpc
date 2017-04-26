package _51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1049 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        long ans = 0, current = 0;
        int n = Reader.nextInt();
        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt();
            current += x;
            ans = Math.max(ans, current);
            current = Math.max(current, 0);
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
