package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CodingContestCreation1 {

    private static boolean ok(int... a) {
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] >= a[i]) return false;
        for (int i = 1; i < a.length; i++)
            if (a[i] - a[i - 1] > 10) return false;
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(new File("1.in"));
        PrintStream cout = new PrintStream("11.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
        //    Scanner cin = new Scanner(System.in);
        //    PrintStream cout = System.out;

        int _case = 0;

        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int n = cin.nextInt();
            int[] a = new int[n + 1];
            int cnt = 1;
            int ans = 0;
            for (int i = 0; i < n; i++) a[i] = cin.nextInt();
            int pre = a[0];
            for (int i = 1; i < n; ) {
                if (pre < a[i] && a[i] - pre <= 10) {
                    pre = a[i++];
                    cnt++;
                } else {
                    ans++;
                    cnt++;
                    pre += 10;
                }

                if (cnt == 4 && i < n) {
                    pre = a[i++];
                    cnt = 1;
                }
            }
            ans += 4 - cnt;

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
