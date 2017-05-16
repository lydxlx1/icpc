package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class BeachUmbrellas {


    static int n, m;
    static int[] r;
    static final long MOD = 1000000007;
    static long[] inv = new long[2222];
    static long[] fac = new long[2222];
    static Map<Integer, Integer>[] map = new HashMap[2222];

    static long pow(long a, long n) {
        long ans = 1;
        while (n > 0) {
            if (n % 2 == 1) ans = ans * a % MOD;
            a = a * a % MOD;
            n /= 2;
        }
        return ans;
    }

    static {
        inv[1] = 1;
        for (int i = 2; i < inv.length; i++)
            inv[i] = pow(i, MOD - 2);

        fac[0] = fac[1] = 1;
        for (int i = 2; i < fac.length; i++)
            fac[i] = fac[i - 1] * i % MOD;

        for (int i = 0; i < map.length; i++)
            map[i] = new HashMap<>();
    }


    private static long choose(int n, int k) {
        if (n < k || n < 0 || k < 0) return 0;
        if (n == k || k == 0) return 1;

        if (map[k].containsKey(n)) return map[k].get(n);
        long ans = n * inv[k] % MOD * choose(n - 1, k - 1) % MOD;
        map[k].put(n, (int) ans);

        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("D.in"));
        PrintStream cout = new PrintStream("D.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            n = cin.nextInt();
            m = cin.nextInt();
            r = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                r[i] = cin.nextInt();
                sum += 2 * r[i];
            }

            long ans = 0;
            if (n == 1) {
                ans = m;
            }
            else {
                for (int i = 0; i < map.length; i++) map[i].clear();

                for (int i = 0; i < n; i++)
                    for (int j = 0; j < n; j++) {
                        if (i == j) continue;

                        int leftLength = m - 1 - sum + r[i] + r[j];
                        ans = (ans + choose(leftLength + n, n)) % MOD;
//                        System.out.println((leftLength + n) + " " + n);
                    }
                ans = ans * fac[n - 2] % MOD;
            }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

}
