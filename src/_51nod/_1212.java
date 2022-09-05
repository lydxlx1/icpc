package _51nod;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class _1212 {

    static int N, M;
    static int[] parent;

    static void makeSet() {
        parent = new int[N];
        for (int i = 0; i < N; i++)
            parent[i] = i;
    }

    static int find(int i) {
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    static void union(int i, int j) {
        parent[find(i)] = find(j);
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Reader.nextInt();
        M = Reader.nextInt();
        int[] u, v, w;
        u = new int[M];
        v = new int[M];
        w = new int[M];
        for (int i = 0; i < M; i++) {
            u[i] = Reader.nextInt() - 1;
            v[i] = Reader.nextInt() - 1;
            w[i] = Reader.nextInt();
        }

        int msp = 0;
        makeSet();
        Integer[] rank = IntStream.range(0, M).boxed()
                .sorted(Comparator.comparingInt((Integer i) -> w[i]))
                .toArray(Integer[]::new);
        for (int i : rank) {
            if (find(u[i]) != find(v[i])) {
                msp += w[i];
                union(u[i], v[i]);
            }
        }

        System.out.println(msp);
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
