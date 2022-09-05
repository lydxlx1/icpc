package google.codejam;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class BitParty {

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

            int R = cin.nextInt();
            int B = cin.nextInt();
            int C = cin.nextInt();
            long[] M = new long[C];
            long[] S = new long[C];
            long[] P = new long[C];

            for (int i = 0; i < C; i++) {
                M[i] = cin.nextInt();
                S[i] = cin.nextInt();
                P[i] = cin.nextInt();
            }

            class Doit {
                boolean isok(long t) {
                    long[] cnt = new long[C];
                    for (int i = 0; i < C; i++) {
                        cnt[i] = Math.min(M[i], Math.max(0L, (t - P[i]) / S[i]));
                    }
                    Arrays.sort(cnt);
                    long total = 0;
                    for (int i = C - 1; i >= C - R; i--) {
                        total += cnt[i];
                    }
                    return total >= B;
                }
            }

            long left = -1, right = Long.MAX_VALUE / 2;
            Doit doit = new Doit();
            while (left + 1 < right) {
                long mid = (left + right) / 2;
                if (doit.isok(mid)) right = mid;
                else left = mid;
            }


            String ans = "" + right;
            cout.printf("Case #%d: %s%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

}
