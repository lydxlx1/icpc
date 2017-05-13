package com._51nod;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static java.util.Comparator.*;

public class _1459 {

    static int n, m, start, end;
    static int[][] g;
    static int[] score;

    static class State {
        int vertex;
        int dist;
        int score;

        public State(int vertex, int dist, int score) {
            this.vertex = vertex;
            this.dist = dist;
            this.score = score;
        }

        public int getVertex() {
            return vertex;
        }

        public int getDist() {
            return dist;
        }

        public int getScore() {
            return score;
        }
    }

    static State dijkstra(int start, int end) {
        State[] state = new State[n];
        for (int i = 0; i < state.length; i++)
            state[i] = new State(i, Integer.MAX_VALUE / 2, 0);
        state[start] = new State(start, 0, score[start]);
        Comparator<State> cmp = Comparator.comparingInt(State::getDist)
                .thenComparing(comparingInt(State::getScore).reversed())
                .thenComparingInt(State::getVertex);
        TreeSet<State> tree = new TreeSet<State>(cmp);
        tree.add(state[start]);
        while (!tree.isEmpty()) {
            State min = tree.pollFirst();
            int u = min.vertex;

            for (int v = 0; v < n; v++)
                if (g[u][v] > 0) {
                    int vDist = min.dist + g[u][v];
                    int vScore = min.score + score[v];
                    State vState = new State(v, vDist, vScore);
                    if (cmp.compare(vState, state[v]) < 0) {
                        tree.remove(state[v]);
                        state[v] = vState;
                        tree.add(state[v]);
                    }
                }
        }
        return state[end];
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Reader.nextInt();
        m = Reader.nextInt();
        start = Reader.nextInt();
        end = Reader.nextInt();
        score = new int[n];
        for (int i = 0; i < n; i++)
            score[i] = Reader.nextInt();
        g = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = Reader.nextInt();
            int v = Reader.nextInt();
            int w = Reader.nextInt();
            g[u][v] = g[v][u] = w;
        }

        State ans = dijkstra(start, end);
        System.out.println(ans.dist + " " + ans.score);

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
