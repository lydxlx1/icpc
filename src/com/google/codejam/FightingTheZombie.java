package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FightingTheZombie {

    private static final int MAX_TRIAL = 25;
    private static final int MAX_POINTS = MAX_TRIAL * 20;

    private static double[][] F(int diceSize) {
        double[][] f = new double[MAX_TRIAL][MAX_POINTS];
        f[0][0] = 1;
        for (int i = 1; i < f.length; i++)
            for (int j = 0; j < f[i].length; j++) {
                for (int k = 1; k <= diceSize; k++)
                    if (j >= k) f[i][j] += f[i - 1][j - k] / diceSize;
            }
        return f;
    }


    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("C.in"));
        PrintStream cout = new PrintStream("C.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        Map<Integer, double[][]> prob = new HashMap<>();
        for (int diceSize : new int[]{4, 6, 8, 10, 12, 20})
            prob.put(diceSize, F(diceSize));

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;
            int H = cin.nextInt();
            int S = cin.nextInt();

            double maxProb = 0;
            for (; S > 0; S--) {
                String str = cin.next();
                String[] tokens = str.split("d");
                int X = Integer.parseInt(tokens[0]);
                int Y, Z;
                if (tokens[1].contains("+") || tokens[1].contains("-")) {
                    Y = Integer.parseInt(tokens[1].split("[+-]")[0]);
                    Z = Integer.parseInt(tokens[1].substring(("" + Y).length()));
                } else {
                    Y = Integer.parseInt(tokens[1]);
                    Z = 0;
                }

                double[][] f = prob.get(Y);
                double p = 0;
                for (int i = 1; i <= X * Y; i++)
                    if (i + Z >= H) p += f[X][i];

                if (p > maxProb) maxProb = p;
            }

            cout.printf("Case #%d: %.13f%n", _case, maxProb);
        }

        cin.close();
        cout.close();
    }
}
