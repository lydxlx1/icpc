package template.data_structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnionFindSet {
    int n;
    int[] parent, pre, next;

    public UnionFindSet(int n) {
        this.n = n;
        parent = new int[n];
        pre = new int[n];
        next = new int[n];
        makeSet();
    }

    public void makeSet() {
        for (int i = 0; i < n; i++) {
            parent[i] = pre[i] = next[i] = i;
        }
    }

    public int find(int i) {
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int i, int j) {
        i = find(i);
        j = find(j);
        parent[i] = j;
        int ii = next[i], jj = next[j];
        next[i] = jj;
        next[j] = ii;
    }

    public List<Integer> collect(int i) {
        List<Integer> res = new ArrayList<>();
        res.add(i);
        for (int j = next[i]; j != i; j = next[j])
            res.add(j);
        return res;
    }

    public Set<Integer> getRoots() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++)
            set.add(find(i));
        return set;
    }
}