package com.codeforces;

import java.io.*;
import java.util.*;

public class _809C {

    static long MOD = 1_000_000_007;
    static Map<List<Integer>, List<Long>> f = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        final int MAX_LEN = 1 << 30;
        StringJoiner joiner = new StringJoiner("\n");
        for (int Q = Reader.nextInt(); Q > 0; Q--) {
            int x1 = Reader.nextInt();
            int y1 = Reader.nextInt();
            int x2 = Reader.nextInt();
            int y2 = Reader.nextInt();
            int cap = Reader.nextInt();

            long ans = G(MAX_LEN, x2, y2, cap).get(0);
            ans -= G(MAX_LEN, x2, y1 - 1, cap).get(0);
            ans -= G(MAX_LEN, x1 - 1, y2, cap).get(0);
            ans += G(MAX_LEN, x1 - 1, y1 - 1, cap).get(0);
            ans = (ans + 4 * MOD) % MOD;
            joiner.add("" + ans);

            f.clear();
        }
        System.out.println(joiner.toString());

        cout.close();
    }

    static List<Long> F(int len, int h, int cap) {
        if (cap < 1)
            return Arrays.asList(0L, 0L);
        if (cap > len)
            return F(len, h, len);
        if (h > len)
            return F(len, len, cap);
        if (len == 1)
            return Arrays.asList(1L, 1L);
        List<Integer> states = Arrays.asList(len, h, cap);
        if (f.containsKey(states))
            return f.get(states);

        long sum = 0;
        long cnt = 0;
        int halfLen = len / 2;
        List<Long> tmp = F(halfLen, h, cap);
        sum += tmp.get(0);
        cnt += tmp.get(1);

        tmp = F(halfLen, h, cap - halfLen);
        sum += tmp.get(0) + tmp.get(1) * halfLen % MOD;
        cnt += tmp.get(1);

        if (h > halfLen) {
            tmp = F(halfLen, h - halfLen, cap);
            sum += tmp.get(0);
            cnt += tmp.get(1);

            tmp = F(halfLen, h - halfLen, cap - halfLen);
            sum += tmp.get(0) + tmp.get(1) * halfLen % MOD;
            cnt += tmp.get(1);
        }

        f.put(states, Arrays.asList(sum % MOD, cnt % MOD));
        return f.get(states);
    }

    static List<Long> G(int len, int r, int c, int cap) {
        if (r < 1 || c < 1 || cap < 1)
            return Arrays.asList(0L, 0L);
        if (r < c)
            return G(len, c, r, cap);
        if (len == 1)
            return F(len, r, cap); // in fact, we have r = c = 1

        // Now, r >= c
        int halfLen = len / 2;
        long sum = 0;
        long cnt = 0;
        List<Long> tmp;

        if (r <= halfLen)
            return G(halfLen, r, c, cap);
        else if (c <= halfLen) {
            tmp = F(halfLen, c, cap);
            sum += tmp.get(0);
            cnt += tmp.get(1);

            tmp = G(halfLen, r - halfLen, c, cap - halfLen);
            sum += tmp.get(0) + halfLen * tmp.get(1) % MOD;
            cnt += tmp.get(1);
        } else {
            tmp = F(halfLen, halfLen, cap);
            sum += tmp.get(0);
            cnt += tmp.get(1);

            tmp = F(halfLen, r - halfLen, cap - halfLen);
            sum += tmp.get(0) + halfLen * tmp.get(1) % MOD;
            cnt += tmp.get(1);

            tmp = F(halfLen, c - halfLen, cap - halfLen);
            sum += tmp.get(0) + halfLen * tmp.get(1) % MOD;
            cnt += tmp.get(1);

            tmp = G(halfLen, r - halfLen, c - halfLen, cap);
            sum += tmp.get(0);
            cnt += tmp.get(1);
        }

        return Arrays.asList(sum % MOD, cnt % MOD);
    }

    static int[][] bruteForce(int n) {
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> visited = new HashSet<>();
                for (int k = 0; k < i; k++)
                    visited.add(ans[k][j]);
                for (int k = 0; k < j; k++)
                    visited.add(ans[i][k]);

                for (int x = 1; ; x++)
                    if (!visited.contains(x)) {
                        ans[i][j] = x;
                        break;
                    }
            }
        }
        return ans;
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