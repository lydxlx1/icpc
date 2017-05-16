package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Haircut {

    static int n;
    static int[] a;
    static int total;

    static long le(long t) {
        if (t < 0)
            return 0;
        long cnt = 0;
        for (int i = 0; i < n; i++)
            cnt += 1 + t / a[i];
        return cnt;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //        Scanner cin = new Scanner(new File("B-small-attempt0.in"));
        //        PrintStream cout = new PrintStream("B-small-attempt0.out");
        Scanner cin = new Scanner(new File("B-large.in"));
        PrintStream cout = new PrintStream("B-large.out");
        //        Scanner cin = new Scanner(System.in);
        //        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            n = cin.nextInt();
            total = cin.nextInt();
            a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = cin.nextInt();
            }

            long left = -1, right = 100000L * total;
            while (left + 1 < right) {
                long mid = (left + right) / 2;
                if (le(mid) >= total)
                    right = mid;
                else
                    left = mid;
            }
            // le(left) < total
            // le(left + 1) >= total
            List<Integer> cand = new ArrayList<Integer>();
            for (int i = 0; i < n; i++)
                if (right % a[i] == 0)
                    cand.add(i + 1);

            cout.printf("Case #%d: %d%n", _case, cand.get((int) (total - le(left) - 1)));
        }

        cin.close();
        cout.close();
    }

}
