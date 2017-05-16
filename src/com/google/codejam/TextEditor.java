package com.google.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class TextEditor {

    static int n, k;

    static class Trie {
        Trie[] ch = new Trie[26];
        boolean is = false;
    }

    static Trie root;

    static void insert(Trie root, String s) {
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            if (root.ch[ch] == null) root.ch[ch] = new Trie();
            root = root.ch[ch];
        }
        root.is = true;
    }

    static final int MAX = Integer.MAX_VALUE / 10;

    static int[] dfs(Trie u) {
        int[] f = new int[k + 1];
        Arrays.fill(f, MAX);
        f[0] = 0;
        if (u != null) {
            for (Trie v : u.ch) {
                int[] g = dfs(v);

                for (int i = k; i >= 0; i--) {
                    for (int j = 0; j <= i; j++) {
                        if (g[j] < MAX) f[i] = Math.min(f[i], f[i - j] + g[j] + 2);
                    }
                }
            }
            if (u.is)
                for (int i = k - 1; i >= 0; i--)
                    f[i + 1] = Math.min(f[i + 1], f[i]);

        }
//        if (u != null) System.out.println(Arrays.toString(f));
        return f;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader cin = new BufferedReader(new FileReader("4.in"));
        PrintStream cout = new PrintStream("4.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = Integer.parseInt(cin.readLine().trim()); T > 0; T--) {
            _case++;

            StringTokenizer token = new StringTokenizer(cin.readLine());
            n = Integer.parseInt(token.nextToken());
            k = Integer.parseInt(token.nextToken());
            root = new Trie();
            for (int i = 0; i < n; i++) insert(root, cin.readLine().trim());

            int[] f = dfs(root);
            int ans = f[k] + k;
            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

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
     *
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
            Queue<State> q = new ArrayDeque<>();

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
            while (SPFA()) adjust();
            return totalCost;
        }
    }
}
