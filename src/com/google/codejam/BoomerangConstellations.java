package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoomerangConstellations {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(new File("1.in"));
        PrintStream cout = new PrintStream("1.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;

        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int n = cin.nextInt();
            long[] x = new long[n];
            long[] y = new long[n];
            for (int i = 0; i < n; i++) {
                x[i] = cin.nextInt();
                y[i] = cin.nextInt();
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                Map<Long, Integer> map = new HashMap<>();
                for (int j = 0; j < n; j++)
                    if (i != j) {
                        long dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                        if (!map.containsKey(dist)) map.put(dist, 0);
                        ans += map.get(dist);
                        map.put(dist, map.get(dist) + 1);
                    }
            }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
