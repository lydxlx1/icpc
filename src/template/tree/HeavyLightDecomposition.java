package template.tree;

import java.util.List;

/*
 * timeStamp is used for segment tree
 * chainRoot points to the root of the heavy chain
 */
public class HeavyLightDecomposition {
    private List<Integer>[] tree;
    private int[] parent;
    private int[] size;
    private int[] depth;
    private int[] timeStamp;
    private int[] timeStampInverse;
    private int[] subtreeLastTimeStamp;
    private int[] chainRoot;
    private int time;

    public HeavyLightDecomposition(List<Integer>[] tree, int root) {
        this.tree = tree;
        this.parent = new int[tree.length];
        this.size = new int[tree.length];
        this.depth = new int[tree.length];
        this.timeStamp = new int[tree.length];
        this.timeStampInverse = new int[tree.length];
        this.subtreeLastTimeStamp = new int[tree.length];
        this.chainRoot = new int[tree.length];
        for (int i = 0; i < tree.length; i++)
            chainRoot[i] = i;
        dfs1(root, -1);
        dfs2(root, -1);
    }

    private void dfs1(int u, int p) {
        size[u] = 1;
        for (int v : tree[u])
            if (v != p) {
                parent[v] = u;
                depth[v] = depth[u] + 1;
                dfs1(v, u);
                size[u] += size[v];
            }
    }

    private void dfs2(int u, int p) {
        timeStamp[u] = time;
        timeStampInverse[time] = u;
        time++;
        subtreeLastTimeStamp[u] = timeStamp[u];
        int heavyChild = -1;
        for (int v : tree[u])
            if (v != p && 2 * size[v] > size[u]) {
                heavyChild = v;
                chainRoot[v] = chainRoot[u];
                dfs2(v, u);
                subtreeLastTimeStamp[u] = subtreeLastTimeStamp[v];
                break;
            }

        for (int v : tree[u])
            if (v != p && v != heavyChild) {
                dfs2(v, u);
                subtreeLastTimeStamp[u] = subtreeLastTimeStamp[v];
            }
    }
}