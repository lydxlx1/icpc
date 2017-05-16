package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class PieProgress {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("A.in"));
        PrintStream cout = new PrintStream("A.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int n = cin.nextInt();
            int m = cin.nextInt();
            long[][] c = new long[n + 1][m + 1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++)
                    c[i][j] = cin.nextInt();

            long[][] f = new long[n + 1][n + 1];
            // f[i][j] means the min cost after the i-th day and have bought j pies.
            // We require that j >= i all the time since we need to eat a pie a day.
            for (int i = 0; i < f.length; i++)
                Arrays.fill(f[i], Long.MAX_VALUE / 10);
            f[0][0] = 0;

            for (int i = 1; i <= n; i++) {
                Arrays.sort(c[i]);
                for (int j = 2; j <= m; j++)
                    c[i][j] += c[i][j - 1]; // convert to prefix sums

                for (int j = i; j <= n; j++) {
                    // Buy k pies at the i-th day.
                    for (int k = 0; k <= m && j - k >= i - 1; k++) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j - k] + c[i][k] + k * k);
                    }
                }
            }
            cout.printf("Case #%d: %d%n", _case, f[n][n]);
        }

        cin.close();
        cout.close();
    }
}
