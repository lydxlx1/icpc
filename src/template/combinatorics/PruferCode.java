package template.combinatorics;

import java.util.*;

public class PruferCode {
    /**
     * The prufer code has n - 1 digits, where the last digit is always the
     * largest leaf.
     */
    static List<Integer> pruferCode(Map<Integer, Set<Integer>> edges) {
        List<Integer> res = new ArrayList<Integer>();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (Integer u : edges.keySet())
            if (edges.get(u).size() == 1) queue.add(u);

        int n = edges.keySet().size();
        for (int t = 0; t < n - 1; t++) {
            Integer u = queue.poll();
            Integer v = edges.get(u).iterator().next();
            // u is the leaf
            // v is the node connected to u
            res.add(v);
            edges.get(u).remove(v);
            edges.get(v).remove(u);
            if (edges.get(v).size() == 1) queue.add(v);
        }
        return res;
    }

    /**
     * Nodes are labeled by 1, 2, ..., n.
     * 
     * Array a has size n - 1, where the last element is always n.
     */
    static Map<Integer, Set<Integer>> decodePruferCode(int[] a) {
        int n = a.length + 1;
        Map<Integer, Set<Integer>> edges = new HashMap<Integer, Set<Integer>>();
        for (int i = 1; i <= n; i++)
            edges.put(i, new HashSet<Integer>());
        int[] counter = new int[n + 1];
        for (int i : a)
            counter[i]++;

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 1; i <= n; i++)
            if (counter[i] == 0) queue.add(i);

        for (int i = 0; i < a.length; i++) {
            int u = queue.poll();
            int v = a[i];

            // u is the leaf
            // v is the connected node
            edges.get(u).add(v);
            edges.get(v).add(u);

            counter[v]--;
            if (counter[v] == 0) queue.add(v);
        }
        return edges;
    }
}
