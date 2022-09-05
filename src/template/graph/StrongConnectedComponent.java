package template.graph;

import java.util.*;

public class StrongConnectedComponent {

    static Map<Integer, Set<Integer>> transpose(Map<Integer, Set<Integer>> g) {
        Map<Integer, Set<Integer>> gg = new HashMap<Integer, Set<Integer>>();
        for (Integer u : g.keySet())
            gg.put(u, new HashSet<Integer>());
        for (Integer u : g.keySet())
            for (Integer v : g.get(u))
                gg.get(v).add(u);
        return gg;
    }

    /**
     * @return the map for each vertex to its corresponding strong connected component
     */
    static Map<Integer, Integer> strongConnectedComponent(final Map<Integer, Set<Integer>> g) {
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        final Map<Integer, Set<Integer>> reversed = transpose(g);
        final Stack<Integer> stack = new Stack<Integer>();
        final Set<Integer> visited = new HashSet<Integer>();

        class Function {
            public void dfs(Integer u) {
                visited.add(u);
                for (Integer v : g.get(u))
                    if (!visited.contains(v))
                        dfs(v);
                stack.push(u);
            }

            public void dfs1(Integer u, Integer color) {
                map.put(u, color);
                visited.add(u);
                for (Integer v : reversed.get(u))
                    if (!visited.contains(v))
                        dfs1(v, color);
            }
        }
        final Function func = new Function();

        for (Integer u : g.keySet())
            if (!visited.contains(u))
                func.dfs(u);

        visited.clear();
        int color = 0;
        while (!stack.isEmpty()) {
            Integer u = stack.pop();
            if (!visited.contains(u))
                func.dfs1(u, color++);
        }
        return map;
    }
}
