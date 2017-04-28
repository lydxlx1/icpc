package _51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1072 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        final int MAX = 2000000;
        int[] f = new int[MAX + 1];
        boolean[] visited = new boolean[MAX + 1];
        int step = 0;
        for (int i = 0; i <= MAX; i++) {
            if (visited[i]) continue;
            f[i] = i + step;
            step++;

            visited[i] = true;
            if (f[i] <= MAX)
                visited[f[i]] = true;
        }

        int T = Reader.nextInt();
        while (T-- > 0) {
            int a = Reader.nextInt();
            int b = Reader.nextInt();
            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }

            if (f[a] == b) System.out.println("B");
            else System.out.println("A");
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
