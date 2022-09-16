package codeforces;

import java.io.*;
import java.util.StringTokenizer;

public class _1619D {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Reader.nextInt();
        while (T-- > 0) {
            int R = Reader.nextInt();
            int C = Reader.nextInt();
            int[][] A = new int[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    A[i][j] = Reader.nextInt();
                }
            }

            int colMaxMin = Integer.MAX_VALUE;
            for (int j = 0; j < C; j++) {
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < R; i++) {
                    max = Math.max(max, A[i][j]);
                }
                colMaxMin = Math.min(colMaxMin, max);
            }

            int rowTop2Max = Integer.MIN_VALUE;
            for (int i = 0; i < R; i++) {
                int top1 = Integer.MIN_VALUE;
                int top2 = Integer.MIN_VALUE;
                for (int j = 0; j < C; j++) {
                    if (A[i][j] > top1) {
                        top2 = top1;
                        top1 = A[i][j];
                    } else {
                        top2 = Math.max(top2, A[i][j]);
                    }
                }
                rowTop2Max = Math.max(rowTop2Max, top2);
            }

            System.out.println(Math.min(colMaxMin, rowTop2Max));
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
