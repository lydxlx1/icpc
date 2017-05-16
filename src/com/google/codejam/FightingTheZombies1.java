package com.google.codejam;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FightingTheZombies1 {

    static int n;
    static int r;
    static Point[] a;


    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("B.in"));
        PrintStream cout = new PrintStream("B.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            n = cin.nextInt();
            r = cin.nextInt();
            a = new Point[n];
            for (int i = 0; i < n; i++) {
                a[i] = new Point(cin.nextInt(), cin.nextInt());
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                int x = a[i].x;
                for (int j = 0; j < n; j++) {
                    int y = a[j].y;

                    long mask1 = 0;
                    for (int idx = 0; idx < n; idx++) {
                        Point p = a[idx];
                        if (p.x >= x && p.x <= x + r && p.y >= y && p.y <= y + r) mask1 = mask1 | (1L << idx);
                    }

                    for (int ii = 0; ii < n; ii++) {
                        int xx = a[ii].x;
                        for (int jj = 0; jj < n; jj++) {
                            int yy = a[jj].y;

                            long mask2 = 0;
                            for (int idx = 0; idx < n; idx++) {
                                Point p = a[idx];
                                if (p.x >= xx && p.x <= xx + r && p.y >= yy && p.y <= yy + r)
                                    mask2 = mask2 | (1L << idx);
                            }

                            ans = Math.max(ans, Long.bitCount(mask1 | mask2));
                        }
                    }
                }
            }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
