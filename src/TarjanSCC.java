import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Template for the Tarjan's algorithm to compute Strong Connected Components (SCC)
 * of a directed graph.
 */
public class TarjanSCC {

    final int n;
    final List<Integer>[] edges;

    static class DS {
        int[] partition;
        boolean[] inStack;
        int[] color;
        int[] stack;
        int ptr;

        int[] ts;
        int[] low;

        int curTs;

        int M; // Number of SCCs

        public DS(int n) {
            partition = new int[n];
            inStack = new boolean[n];
            color = new int[n];
            stack = new int[n];
            ts = new int[n];
            low = new int[n];
            ptr = 0;
            curTs = 0;
            M = 0;
        }
    }

    /**
     * @param n     Number of vertices
     * @param edges Adjacent list.
     * @note All edges u -> v must have 0 <= u, v < n
     * Parallel edges & self-loops are allowed.
     */
    public TarjanSCC(int n, List<Integer>[] edges) {
        this.n = n;
        this.edges = edges;
    }

    /**
     * @return an array of size n representing the vertex partition.
     * Vertices in the same SCC will be assigned the same SCC index.
     * All SCC indices are between 0 and M - 1, where M = # of SCCs
     */
    public int[] computeSCC() {
        DS ds = new DS(n);
        for (int i = 0; i < n; i++) {
            if (ds.color[i] == 0) {
                dfs(i, ds);
            }
        }
        return ds.partition.clone();
    }

    private void dfs(int u, DS ds) {
        ds.color[u] = 1;

        ds.low[u] = ds.ts[u] = ds.curTs++;
        ds.stack[ds.ptr++] = u;
        ds.inStack[u] = true;

        for (int v : edges[u]) {
            if (ds.color[v] == 0) {
                dfs(v, ds);
                ds.low[u] = Math.min(ds.low[u], ds.low[v]);
            } else if (ds.inStack[v]) {
                ds.low[u] = Math.min(ds.low[u], ds.ts[v]);
            }
        }

        if (ds.low[u] == ds.ts[u]) {
            int w = 0;
            do {
                w = ds.stack[--ds.ptr];
                ds.partition[w] = ds.M;
                ds.inStack[w] = false;
            }
            while (w != u);
            ds.M++;
        }

        ds.color[u] = 2;
    }

    public static void main(String[] args) {
        int n = 5;
        List<Integer>[] edges = new List[]{
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4),
                Arrays.asList(0),
                Arrays.asList(),
        };
        TarjanSCC scc = new TarjanSCC(n, edges);
        int[] partition = scc.computeSCC();
        System.out.println(Arrays.toString(partition));
    }
}

