package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ManicMoving {

    static int n, m, k;
    static int[][] g;
    static int[] s, d;
    static final int INF = Integer.MAX_VALUE / 10;

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("C.in"));
        PrintStream cout = new PrintStream("C.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            n = cin.nextInt();
            m = cin.nextInt();
            k = cin.nextInt();
            g = new int[n + 1][n + 1];
            s = new int[k + 1];
            d = new int[k + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    g[i][j] = INF;
                }
                g[i][i] = 0;
            }
            for (int i = 0; i < m; i++) {
                int u = cin.nextInt();
                int v = cin.nextInt();
                int w = cin.nextInt();
                g[u][v] = g[v][u] = Math.min(g[u][v], w);
            }
            for (int i = 1; i <= k; i++) {
                s[i] = cin.nextInt();
                d[i] = cin.nextInt();
            }

            // Run Floyd Algorithm
            for (int t = 1; t <= n; t++)
                for (int i = 1; i <= n; i++)
                    for (int j = 1; j <= n; j++)
                        g[i][j] = Math.min(g[i][j], g[i][t] + g[t][j]);

            boolean noSolution = false;
            for (int i = 1; i <= k; i++)
                if (g[1][s[i]] >= INF || g[1][d[i]] >= INF) {
                    noSolution = true;
                    break;
                }

            int ans = Integer.MAX_VALUE;
            if (noSolution) ans = -1;
            else {
                int[][] f = new int[k + 1][n + 1];
                for (int i = 0; i < f.length; i++)
                    Arrays.fill(f[i], INF);

                f[1][s[1]] = g[1][s[1]];
                PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(u -> f[u[0]][u[1]]));
                queue.add(new int[]{1, s[1]});
                while (!queue.isEmpty()) {
                    int family = queue.peek()[0];
                    int where = queue.poll()[1];
                    int cost = f[family][where];
//                    System.out.printf("f[%d][%d] = %d%n", family, where, cost);

                    if (family == k) continue;

                    // Case 1: unload the current family immediately, then pick up the next family
                    int newFamily = family + 1;
                    int newWhere = s[newFamily];
                    int newCost = cost + g[where][d[family]] + g[d[family]][s[newFamily]];
                    if (newCost < f[newFamily][newWhere]) {
                        f[newFamily][newWhere] = newCost;
                        queue.add(new int[]{newFamily, newWhere});
                    }

                    // Case 2: load the next family. Now the truck is full and must unload the previous family.
                    newWhere = d[family];
                    newCost = cost + g[where][s[newFamily]] + g[s[newFamily]][d[family]];
                    if (newCost < f[newFamily][newWhere]) {
                        f[newFamily][newWhere] = newCost;
                        queue.add(new int[]{newFamily, newWhere});
                    }
                }

                for (int where = 1; where <= n; where++)
                    if (f[k][where] < INF) {
                        ans = Math.min(ans, f[k][where] + g[where][d[k]]);
                    }
            }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

}
