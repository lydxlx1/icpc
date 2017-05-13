package com.google.codejam.past;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LaundroMatt {

    static class Washer {
        long t;
        int id;

        public Washer(long t, int id) {
            this.t = t;
            this.id = id;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader cin = new BufferedReader(new FileReader("2.in"));
        PrintStream cout = new PrintStream("2.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
        //    Scanner cin = new Scanner(System.in);
        //    PrintStream cout = System.out;

        int _case = 0;
        StringTokenizer token = new StringTokenizer(cin.readLine());
        for (int T = Integer.parseInt(token.nextToken().trim()); T > 0; T--) {
            _case++;

            token = new StringTokenizer(cin.readLine());
            int L = Integer.parseInt(token.nextToken());
            int N = Integer.parseInt(token.nextToken());
            int M = Integer.parseInt(token.nextToken());
            long D = Integer.parseInt(token.nextToken());
            M = Math.min(L, M);

            long[] W = new long[N];
            token = new StringTokenizer(cin.readLine());
            for (int i = 0; i < N; i++) W[i] = Integer.parseInt(token.nextToken());

            boolean[] visited = new boolean[N];
            final long[] washerTimer = new long[N];
            PriorityQueue<Integer> washer = new PriorityQueue<>((u, v) -> Long.compare(washerTimer[u], washerTimer[v]));
            for (int i = 0; i < N; i++) washerTimer[i] = W[i];
            for (int i = 0; i < N; i++) washer.add(i);
            long[] a = new long[L];
            for (int i = 0; i < a.length; i++) {
                int id = washer.poll();
                a[i] = washerTimer[id];
                washerTimer[id] += W[id];
                washer.add(id);
            }

            PriorityQueue<Long> dryer = new PriorityQueue<>();
            for (int i = 0; i < M; i++) dryer.add(0L);
            for (int i = 0; i < a.length; i++)
                dryer.add(Math.max(dryer.poll(), a[i]) + D);

            long ans = 0;
            for (long elem : dryer) ans = Math.max(ans, elem);
            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
