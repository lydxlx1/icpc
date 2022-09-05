package template.graph;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumCostMaxFlow {

    /*
    2. pku 2135 最小费用最大流，适用于稀疏图，允许多重边，PriorityQueue优化SPFA
    注意！ 在求最大费用流的时候，如果建的图可以保证是DAG，那么可以直接把边的权重*-1；否则需要加上一个非常大的数M，最后再减去。
    */
    static class List {
        int vertex;
        int capacity;
        int cost;
        List twin;
        List next;

        public List(int vertex, int capacity, int cost) {
            this.vertex = vertex;
            this.capacity = capacity;
            this.cost = cost;
        }
    }

    /***
     * Assume source = 0, sink = n - 1.
     * Please new the class for each query
     * @author yuan
     */
    static class Graph {
        int n;
        List edge[];
        int dist[];
        int pre[];
        List preEdge[];
        boolean visited[];
        int maxFlow;
        int totalCost;
        final int INF = Integer.MAX_VALUE >> 1; // Need to be careful here

        public Graph(int n) {
            this.n = n;
            edge = new List[n];
            for (int i = 0; i < n; i++)
                edge[i] = new List(0, 0, 0); // dummy node
        }

        public void add(int u, int v, int capacity, int cost) {
            List ci = new List(v, capacity, cost);
            List cici = new List(u, 0, -cost);
            ci.twin = cici;
            cici.twin = ci;
            ci.next = edge[u].next;
            edge[u].next = ci;
            cici.next = edge[v].next;
            edge[v].next = cici;
        }

        static class State implements Comparable<State> {
            int vertex;
            int dist;

            public State(int vertex, int dist) {
                this.vertex = vertex;
                this.dist = dist;
            }

            @Override
            public int compareTo(State o) {
                return ((Integer) dist).compareTo(o.dist);
            }
        }

        private boolean SPFA() {
            dist = new int[n];
            visited = new boolean[n];
            pre = new int[n];
            preEdge = new List[n];
            Queue<State> q = new LinkedList<State>();

            q.add(new State(0, 0));
            for (int i = 0; i < n; i++)
                dist[i] = INF;
            dist[0] = 0;
            visited[0] = true;

            while (!q.isEmpty()) {
                while (true) {
                    int u = q.peek().vertex;
                    int d = q.peek().dist;
                    if (d > dist[u]) {
                        q.poll();
                        q.add(new State(u, dist[u]));
                    } else break;
                }

                int u = q.poll().vertex;
                visited[u] = false;
                for (List v = edge[u].next; v != null; v = v.next)
                    if (v.capacity > 0 && dist[u] + v.cost < dist[v.vertex]) {
                        dist[v.vertex] = dist[u] + v.cost;
                        pre[v.vertex] = u;
                        preEdge[v.vertex] = v;
                        if (!visited[v.vertex]) {
                            visited[v.vertex] = true;
                            q.add(new State(v.vertex, dist[v.vertex]));
                        }
                    }
            }
            return dist[n - 1] < INF;
        }

        private void adjust() {
            int flow = Integer.MAX_VALUE;
            for (int i = n - 1; i != pre[i]; i = pre[i])
                flow = Math.min(flow, preEdge[i].capacity);
            for (int i = n - 1; i != pre[i]; i = pre[i]) {
                preEdge[i].capacity -= flow;
                preEdge[i].twin.capacity += flow;
                totalCost += flow * preEdge[i].cost;
            }
            maxFlow += flow;
        }

        public int minCostMaxFlow() {
            while (SPFA())
                adjust();
            return totalCost;
        }
    }
}
