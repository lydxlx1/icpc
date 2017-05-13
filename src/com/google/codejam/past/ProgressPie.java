package com.google.codejam.past;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class ProgressPie {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int T = cin.nextInt();
        for (int kase = 1; kase <= T; kase++) {
            int P = cin.nextInt();
            int X = cin.nextInt();
            int Y = cin.nextInt();
            System.out.println(String.format("Case #%d: %s", kase, whichColor(P, X, Y)));
        }
        cin.close();
    }

    private static String whichColor(int P, int X, int Y) {
        int x = X - 50;
        int y = Y - 50;
        if (x * x + y * y > 50 * 50) return "white";

        // Now, the point is guaranteed to lie inside the disc
        if (P == 0) return "white";
        if (P == 100) return "black";
        if (X == 50 && Y == 50) return "black";

        // For each query point (X, Y), we
        // 1. Shift by (-50, 50)
        // 2. Counter-clockwise rotate 270 degree
        // 3. Flip along x-axis
        // Now, the transformed point is located at (Y - 50, X - 50)
        double angle = Math.atan2(X - 50, Y - 50) / 2 / Math.PI;
        if (angle < 0) angle++;
//        System.out.println(angle);
        return angle < (P / 100.0) ? "black" : "white";
    }
}

