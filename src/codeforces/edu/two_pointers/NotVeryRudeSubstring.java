package codeforces.edu.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NotVeryRudeSubstring {

    static {
        Reader.init(System.in);
    }

    public static void main(String[] args) throws Exception {
        int n = Reader.nextInt();
        long c = Long.parseLong(Reader.next());
        char[] s = Reader.next().toCharArray();

        int left = 1, right = n + 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;

            int cntA = 0, cntB = 0;
            long rudeness = 0;
            boolean ok = false;
            for (int i = 0; i < n; i++) {
                if (i - mid >= 0) {
                    if (s[i - mid] == 'a') {
                        cntA--;
                        rudeness -= cntB;
                    } else if (s[i - mid] == 'b') {
                        cntB--;
                    }
                }

                if (s[i] == 'a') {
                    cntA++;
                } else if (s[i] == 'b') {
                    rudeness += cntA;
                    cntB++;
                }

                if (i >= mid - 1 && rudeness <= c) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
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
