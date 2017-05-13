package com.google.codejam.past;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class LazyLoading {
    public static void main(String[] args) throws Exception {
        Scanner cin = new Scanner(new File("B.in"));
        BufferedWriter cout = new BufferedWriter(new FileWriter(new File("B.out")));

        int T = cin.nextInt();
        for (int ccase = 1; ccase <= T; ccase++) {
            int n = cin.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = cin.nextInt();
            Arrays.sort(a);

            int ans = 0;
            for (int i = 0, j = n - 1; i <= j; ) {
                if (a[j] < 50) {
                    int need = (50 + a[j] - 1) / a[j];
                    int actual = j - i + 1;
                    if (actual < need) break;
                    i += need - 1;
                }
                ans++;
                j--;
            }
            cout.write(String.format("Case #%d: %d%n", ccase, ans));
        }

        cin.close();
        cout.close();
    }
}

