package codeforces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _1843D {
    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Reader.nextInt();
        while (T-- > 0) {
            int N = Reader.nextInt();
            List<Integer>[] adj = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }
            for (int i = 1; i < N; i++) {
                int u = Reader.nextInt();
                int v = Reader.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }
            int[] numLeaves = new int[N + 1];


            class DFS {
                int dfs(int u, int p) {
                    boolean isLeaf = true;
                    for (int v : adj[u]) {
                        if (v != p) {
                            numLeaves[u] += dfs(v, u);
                            isLeaf = false;
                        }
                    }
                    numLeaves[u] += isLeaf ? 1 : 0;
                    return numLeaves[u];
                }
            }
            new DFS().dfs(1, -1);

            int Q = Reader.nextInt();
            while (Q-- > 0) {
                int x = Reader.nextInt();
                int y = Reader.nextInt();
                long ans = 1L * numLeaves[x] * numLeaves[y];
                cout.write(ans + "\n");
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
