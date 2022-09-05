package template.tree;

import java.util.Arrays;

public class LowestCommonAncestor {
  private final int[][] edges;
  private final int[] depth;
  private final int[][] ancestor; // ancestor[i][j] is the (2^j)-th ancestor of i, or -1 if it does not exist.

  public LowestCommonAncestor(int V, int root, int[][] edges) {
    this.edges = edges;
    this.depth = new int[V];
    for (int log = 0;; log++)
      if ((1 << log) >= V) {
        ancestor = new int[V][log + 1];
        break;
      }
    for (int[] arr : ancestor)
      Arrays.fill(arr, -1);

    dfs(root, -1);
    for (int p = 1; p < ancestor[0].length; p++)
      for (int i = 0; i < V; i++) {
        int half = ancestor[i][p - 1];
        if (half != -1)
          ancestor[i][p] = ancestor[half][p - 1];
      }
  }

  public int getLowestCommonAncestor(int u, int v) {
    if (depth[u] < depth[v])
      return getLowestCommonAncestor(v, u);
    for (int i = ancestor[0].length - 1; i >= 0; i--)
      if (depth[u] - (1 << i) >= depth[v]) {
        u = ancestor[u][i];
      }

    if (u == v)
      return u;

    for (int i = ancestor[0].length - 1; i >= 0; i--)
      if (ancestor[u][i] != -1 && ancestor[u][i] != ancestor[v][i]) {
        u = ancestor[u][i];
        v = ancestor[v][i];
      }

    return ancestor[u][0];
  }

  private void dfs(int u, int parent) {
    for (int v : edges[u]) {
      if (v != parent) {
        depth[v] = depth[u] + 1;
        ancestor[v][0] = u;
        dfs(v, u);
      }
    }
  }
}

class AdjacentListGenerator {
  private int V;
  private int edges[][];
  private int size;

  /**
   * @param V Number of vertices
   * @param MAX_EDGE_NUM Approximation of number of edges. Try to assign a sufficiently large size to avoid reallocating memory.
   */
  public AdjacentListGenerator(int V, int MAX_EDGE_NUM) {
    this.V = V;
    edges = new int[MAX_EDGE_NUM][2];
    size = 0;
  }

  private void doubleSize() {
    int[][] newEdges = new int[edges.length * 2][2];
    for (int i = 0; i < size; i++) {
      newEdges[i][0] = edges[i][0];
      newEdges[i][1] = edges[i][1];
    }
    edges = newEdges;
  }

  /**
   * Add a direct edge from {@code from} to {@code to}.
   * @param from
   * @param to
   */
  public void addEdge(int from, int to) {
    if (size >= edges.length) {
      doubleSize();
    }
    edges[size][0] = from;
    edges[size][1] = to;
    size++;
  }

  public int[][] getAdacentList() {
    int[][] res = new int[V][];
    int[] degree = new int[V];
    for (int i = 0; i < size; i++) {
      degree[edges[i][0]]++;
    }
    for (int i = 0; i < V; i++) {
      res[i] = new int[degree[i]];
    }
    for (int i = 0; i < size; i++) {
      int u = edges[i][0], v = edges[i][1];
      res[u][--degree[u]] = v;
    }
    return res;
  }

  public int[][] getReversedAdacentList() {
    int[][] res = new int[V][];
    int[] degree = new int[V];
    for (int i = 0; i < size; i++) {
      degree[edges[i][1]]++;
    }
    for (int i = 0; i < V; i++) {
      res[i] = new int[degree[i]];
    }
    for (int i = 0; i < size; i++) {
      int u = edges[i][1], v = edges[i][0];
      res[u][--degree[u]] = v;
    }
    return res;
  }
}
