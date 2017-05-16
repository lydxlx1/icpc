package com.google.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class ThePriceIsCorrect {

    public static void main(String[] args) throws Exception {
        BufferedReader cin = new BufferedReader(new FileReader("3.in"));
        PrintStream cout = new PrintStream("3.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = Integer.parseInt(cin.readLine().trim()); T > 0; T--) {
            _case++;

            StringTokenizer token = new StringTokenizer(cin.readLine());
            int n = Integer.parseInt(token.nextToken());
            long p = Integer.parseInt(token.nextToken());
            long[] b = new long[n];
            long[] sum = new long[n];
            token = new StringTokenizer(cin.readLine());
            for (int i = 0; i < n; i++) b[i] = Integer.parseInt(token.nextToken());
            sum[0] = b[0];
            for (int i = 1; i < n; i++) sum[i] = sum[i - 1] + b[i];

            long ans = 0;
            int right = 0;
            for (int left = 0; left < n; left++) {
                if (right < left) right = left;
                while (right < n && sum[right] <= p + (left - 1 >= 0 ? sum[left - 1] : 0)) right++;
                ans += right - left;
            }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
