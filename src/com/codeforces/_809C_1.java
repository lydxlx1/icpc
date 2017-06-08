package com.codeforces;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;


public class _809C_1 {

    /**
     * Immutable class
     */
    static class Key {
        final int len;
        final int height;
        final int cap;
        final int hash;

        public Key(int len, int height, int cap) {
            this.len = len;
            this.height = height;
            this.cap = cap;
            int h = len;
            h = h * 31 + height;
            h = h * 31 + cap;
            this.hash = h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (len != key.len) return false;
            if (height != key.height) return false;
            return cap == key.cap;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public String toString() {
            return "Key{" + "len=" + len + ", height=" + height + ", cap=" + cap + '}';
        }
    }

    static class Value {
        long cnt;
        long sum;

        @Override
        public String toString() {
            return "Value{" + "cnt=" + cnt + ", sum=" + sum + '}';
        }
    }

    static final int MOD = 1000000007;
    static final int INV_2 = 500000004;
    static Map<Key, Value> f = new HashMap<>();

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
            long ans = g(MAX_LEN, x2, y2, cap).sum;
            ans -= g(MAX_LEN, x1 - 1, y2, cap).sum;
            ans -= g(MAX_LEN, x2, y1 - 1, cap).sum;
            ans += g(MAX_LEN, x1 - 1, y1 - 1, cap).sum;
            ans += 2L * MOD;
            joiner.add("" + (ans % MOD));
            System.out.println(f.size() / 4);
            f.clear();
        }
        System.out.println(joiner.toString());

        cout.close();
    }

    static Value f(int len, int height, int cap) {
        cap = Math.max(cap, 0);
        cap = Math.min(cap, len);
        height = Math.max(height, 0);
        height = Math.min(height, len);

        Key key = new Key(len, height, cap);
        if (f.containsKey(key)) return f.get(key);

        Value value = new Value();
        if (cap < 1 || height < 1)
            value.cnt = value.sum = 0;
        else if (cap == len) {
            value.cnt = (long) len * height % MOD;
            value.sum = (1L + len) * len % MOD * INV_2 % MOD * height % MOD;
        } else {
            int halfLen = len / 2;
            add(value, f(halfLen, height, cap), 0);
            add(value, f(halfLen, height, cap - halfLen), halfLen);
            add(value, f(halfLen, height - halfLen, cap - halfLen), halfLen);
            add(value, f(halfLen, height - halfLen, cap), 0);
        }

        f.put(key, value);
        return value;
    }

    static void add(Value acc, Value v, int extra) {
        acc.cnt = (acc.cnt + v.cnt) % MOD;
        acc.sum = (acc.sum + v.sum + v.cnt * extra) % MOD;
    }

    static Value g(int len, int height, int width, int cap) {
        height = Math.min(height, len);
        width = Math.min(width, len);
        if (height > width)
            return g(len, width, height, cap);
        Value value = new Value();
        if (cap < 1 || height < 1)
            value.cnt = value.sum = 0;
        else if (len == 1)
            value.cnt = value.sum = 1;
        else {
            int halfLen = len / 2;
            if (width <= halfLen)
                add(value, g(halfLen, height, width, cap), 0);
            else if (height <= halfLen) {
                add(value, f(halfLen, height, cap), 0);
                add(value, g(halfLen, height, width - halfLen, cap - halfLen), halfLen);
            } else {
                add(value, f(halfLen, halfLen, cap), 0);
                add(value, f(halfLen, height - halfLen, cap - halfLen), halfLen);
                add(value, f(halfLen, width - halfLen, cap - halfLen), halfLen);
                add(value, g(halfLen, height - halfLen, width - halfLen, cap), 0);
            }
        }
        return value;
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
