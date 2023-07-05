package codeforces;

import java.io.*;
import java.util.StringTokenizer;

public class _1843E {
    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Reader.nextInt();
        while (T-- > 0) {
            int N = Reader.nextInt(), M = Reader.nextInt();
            int[][] interval = new int[M][2];
            for (int i = 0; i < M; i++) {
                interval[i][0] = Reader.nextInt();
                interval[i][1] = Reader.nextInt();
            }
            int Q = Reader.nextInt();
            int[] query = new int[Q];
            for (int i = 0; i < Q; i++) {
                query[i] = Reader.nextInt();
            }

            class Check {
                boolean check(int firstQ) {
                    int[] cnt = new int[N + 1];
                    for (int i = 0; i < firstQ; i++) {
                        cnt[query[i]] = 1;
                    }
                    for (int i = 1; i < cnt.length; i++) {
                        cnt[i] += cnt[i - 1];
                    }

                    for (int[] it : interval) {
                        int x = it[0], y = it[1];
                        if (2 * (cnt[y] - cnt[x - 1]) > y - x + 1) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            var check = new Check();

            if (!check.check(Q)) {
                cout.write("-1\n");
            } else {
                int L = 0, R = Q; // L is infeasible, R is feasible
                while (L + 1 < R) {
                    int mid = (L + R) / 2;
                    if (check.check(mid)) {
                        R = mid;
                    } else {
                        L = mid;
                    }
                }
                cout.write(R + "\n");
            }
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
