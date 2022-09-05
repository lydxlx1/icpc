package google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class RedTapeCommittee {


    static int n, k;
    static double[] p;
    static double[] cand;
    static double[][] f;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(new File("B-small-attempt0.in"));
        PrintStream cout = new PrintStream("B-small-attempt0.out");
//        Scanner cin = new Scanner(new File("A-large.in"));
//        PrintStream cout = new PrintStream("A-large.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;

        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;
            StringBuilder ans = new StringBuilder();

            n = cin.nextInt();
            k = cin.nextInt();
            p = new double[n];
            cand = new double[k];
            double max = 0;

            for (int i = 0; i < n; i++) p[i] = cin.nextDouble();
            for (int mask = 0; mask < (1 << n); mask++)
                if (Integer.bitCount(mask) == k) {
                    for (int i = 0, ptr = 0; i < n; i++)
                        if (((1 << i) & mask) != 0) cand[ptr++] = p[i];

                    f = new double[k][k + 1];
                    f[0][0] = 1 - cand[0];
                    f[0][1] = cand[0];
                    for (int i = 1; i < k; i++)
                        for (int j = 0; j <= k; j++) {
                            f[i][j] = (1 - cand[i]) * f[i - 1][j]; // vote for "NO"
                            if (j > 0) f[i][j] += cand[i] * f[i - 1][j - 1]; // vote for "YES"
                        }
                    max = Math.max(max, f[k - 1][k / 2]);
                }
            ans.append(String.format("%.10f", max));

            cout.printf("Case #%d: %s%n", _case, ans.toString());
        }

        cin.close();
        cout.close();
    }
}
