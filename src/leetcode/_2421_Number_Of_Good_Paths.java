package leetcode;

import java.io.*;
import java.util.*;

public class _2421_Number_Of_Good_Paths {

    int[] parent;
    int n;
    int[] vals;
    int currentVal;
    Map<Integer, Integer>[] valCnt;

    int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    void union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i != j) {
            parent[j] = i;
            valCnt[i].put(currentVal, valCnt[i].getOrDefault(currentVal, 0) + valCnt[j].getOrDefault(currentVal, 0));
        }
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        n = vals.length;
        parent = new int[n];
        this.vals = vals;
        valCnt = new Map[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            valCnt[i] = new HashMap<>();
        }

        List<List<Integer>> G = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            G.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            G.get(u).add(v);
            G.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        Integer[] sortedNode = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedNode[i] = i;
        }
        Arrays.sort(sortedNode, (u, v) -> vals[u] - vals[v]);

        int ans = 0;
        for (int i = 0; i < n; ) {
            int ii = i;
            currentVal = vals[sortedNode[i]];

            // Init cnts w.r.t. currentVal
            for (ii = i; ii < n && currentVal == vals[sortedNode[ii]]; ii++) {
                int u = sortedNode[ii];
                valCnt[u].put(currentVal, 1);
            }

            // Connect the forest
            for (ii = i; ii < n && currentVal == vals[sortedNode[ii]]; ii++) {
                int u = sortedNode[ii];
                for (int v : G.get(u)) {
                    if (vals[v] <= currentVal) {
                        union(u, v);
                    }
                }
            }

            // Aggregate solutions per each tree
            Set<Integer> roots = new HashSet<>();
            for (ii = i; ii < n && currentVal == vals[sortedNode[ii]]; ii++) {
                int u = sortedNode[ii];
                roots.add(find(u));
            }
            for (int u : roots) {
                int sz = valCnt[u].getOrDefault(currentVal, 0);
                ans += sz * (sz - 1) / 2;
            }

            i = ii;
        }

        return ans + n;
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        _2421_Number_Of_Good_Paths sol = new _2421_Number_Of_Good_Paths();
        {
            int[] vals = {1, 3, 2, 1, 3};
            int[][] edges = {
                    {0, 1},
                    {0, 2},
                    {2, 3},
                    {2, 4},
            };
            System.out.println(sol.numberOfGoodPaths(vals, edges));
        }
        {
            int[] vals = {1, 1, 2, 2, 3};
            int[][] edges = {
                    {0, 1},
                    {1, 2},
                    {2, 3},
                    {2, 4},
            };
            System.out.println(sol.numberOfGoodPaths(vals, edges));
        }
        {
            int[] vals = {1};
            int[][] edges = {
            };
            System.out.println(sol.numberOfGoodPaths(vals, edges));
        }
        {
            int[] vals = {2, 5, 5, 1, 5, 2, 3, 5, 1, 5};
            int[][] edges = {{0, 1}, {2, 1}, {3, 2}, {3, 4}, {3, 5}, {5, 6}, {1, 7}, {8, 4}, {9, 7}};
            System.out.println(sol.numberOfGoodPaths(vals, edges));

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
