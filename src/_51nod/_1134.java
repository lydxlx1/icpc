package _51nod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _1134 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        List<Integer> f = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt();
            if (f.isEmpty() || f.get(f.size() - 1) < x)
                f.add(x);
            else {
                int left = -1, right = f.size() - 1;
                while (left + 1 < right) {
                    int mid = (left + right) / 2;
                    if (f.get(mid) >= x) right = mid;
                    else left = mid;
                }
                f.set(right, x);
            }
        }
        System.out.println(f.size());

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
