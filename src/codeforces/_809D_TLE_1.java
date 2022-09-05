package codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * An O(n^2)-time DP solution, which obviously receives a TLE.
 */
public class _809D_TLE_1 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> dp = new ArrayList<>();
        int n = Reader.nextInt();
        for (int i = 0; i < n; i++) {
            int x = Reader.nextInt();
            int y = Reader.nextInt();

            int m = dp.size();
            for (int j = m; j >= 0; j--) {
                int xx = j - 1 >= 0 ? dp.get(j - 1) + 1 : 1;
                int yy = j < m ? dp.get(j) : Integer.MAX_VALUE;
                xx = Math.max(xx, x);
                yy = Math.min(yy, y);
                if (xx <= yy) {
                    if (j == m)
                        dp.add(xx);
                    else
                        dp.set(j, xx);
                }
            }
//            System.out.println(dp);
        }
        System.out.println(dp.size());

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
