package com.google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class HighSecurity {

    static void put(char[][] a, int x, int y) {
        a[x][y] = a[1 - x][y] = 'X';
        for (int j = y - 1; j >= 0; j--)
            if (a[x][j] == '.') a[x][j] = 'X';
            else break;
        for (int j = y + 1; j < a[x].length; j++)
            if (a[x][j] == '.') a[x][j] = 'X';
            else break;
    }

    static boolean doit(char[][] a) {
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++)
                if (a[i][j] == '.' && (j - 1 < 0 || a[i][j - 1] == 'X') && (j + 1 >= a[i].length || a[i][j + 1] == 'X')) {
                    if (a[1 - i][j] == '.') put(a, 1 - i, j);
                    else put(a, i, j);
                    return true;
                }

        for (int j = 0; j < a[0].length; j++)
            for (int i = 0; i < a.length; i++)
                if (a[i][j] == '.') {
                    put(a, i, j);
                    return true;
                }

        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(new File("2.in"));
        PrintStream cout = new PrintStream("2.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int n = cin.nextInt();
            char[][] a = new char[2][];
            a[0] = cin.next().toCharArray();
            a[1] = cin.next().toCharArray();

            int ans = 0;
            while (doit(a)) {
                ans++;
//                for (int i = 0; i < 2; i++) System.out.println(String.valueOf(a[i]));
//                System.out.println();
            }
            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
