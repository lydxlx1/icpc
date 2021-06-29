package com.codeforces.edu.two_pointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LoopedPlaylist {

    static {
        Reader.init(System.in);
    }

    public static void main(String[] args) throws Exception {
        int n = Reader.nextInt();
        long happy = Long.parseLong(Reader.next());
        int[] song = new int[n];

        long total = 0;
        for (int i = 0; i < n; i++) {
            song[i] = Reader.nextInt();
            total += song[i];
        }

        long pre_cnt = happy / total * n;
        happy %= total;
        if (happy == 0) {
            System.out.println(1 + " " + pre_cnt);
        } else {
            int len = 1 << 29;
            int begin = 0;
            for (int i = 0; i < n; i++) {
                long cnt = 0;
                for (int j = 0; j < n; j++) {
                    cnt += song[(i + j) % n];
                    if (cnt >= happy) {
                        if (j + 1 < len) {
                            len = j + 1;
                            begin = i + 1;
                        }
                        break;
                    }
                }
            }
            System.out.println(begin + " " + (len + pre_cnt));
        }
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
