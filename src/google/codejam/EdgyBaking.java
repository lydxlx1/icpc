package google.codejam;

import java.io.PrintStream;
import java.util.Scanner;

public class EdgyBaking {

    public static void main(String[] args) throws Exception {
//        Scanner cin = new Scanner(new File("B-small-attempt0.in"));
//        PrintStream cout = new PrintStream("B-small-attempt0.out");
//        Scanner cin = new Scanner(new File("B-large.in"));
//        PrintStream cout = new PrintStream("B-large.out");
        Scanner cin = new Scanner(System.in);
        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int N = cin.nextInt();
            int P = cin.nextInt();
            int PP = 0;
            int[] a = new int[N];
            int[] b = new int[N];


            int W = 0;
            for (int i = 0; i < N; i++) {
                a[i] = cin.nextInt();
                b[i] = cin.nextInt();
                W += Math.min(a[i], b[i]);
                PP += 2 * (a[i] + b[i]);
                P -= 2 * (a[i] + b[i]);
            }

            W = Math.min(W, P / 2);
            double[] f = new double[W + 1];

            for (int i = 0; i < N; i++) {
                double v = 2 * Math.sqrt((long) a[i] * a[i] + (long) b[i] * b[i]);
                int w = Math.min(a[i], b[i]);
                for (int j = f.length - 1; j - w >= 0; j--) {
                    f[j] = Math.max(f[j], f[j - w] + v);
                }
            }

            double ans = Math.min(f[W], P) + PP;


            cout.printf("Case #%d: %.10f%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

}
