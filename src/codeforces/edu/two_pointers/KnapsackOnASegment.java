package codeforces.edu.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapsackOnASegment {

    static {
        Reader.init(System.in);
    }

    public static void main(String[] args) throws Exception {
        int n = Reader.nextInt();
        long W = Reader.nextInt();
        int[] w = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = Reader.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = Reader.nextInt();
        }

        long ans = 0;
        long curW = 0, curC = 0;
        for (int i = 0, j = 0; j < n; j++) {
            curW += w[j];
            curC += c[j];

            while (curW > W) {
                curW -= w[i];
                curC -= c[i];
                i++;
            }

            ans = Math.max(ans, curC);
        }
        System.out.println(ans);
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
