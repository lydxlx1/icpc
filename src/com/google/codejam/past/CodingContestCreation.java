import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class CodingContestCreation {

    private static boolean ok(int... a) {
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] >= a[i]) return false;
        for (int i = 1; i < a.length; i++)
            if (a[i] - a[i - 1] > 10) return false;
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(new File("1.in"));
        PrintStream cout = new PrintStream("1.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
        //    Scanner cin = new Scanner(System.in);
        //    PrintStream cout = System.out;

        int _case = 0;

        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int n = cin.nextInt();
            int[] a = new int[n + 1];
            long[] f = new long[n + 1];

            for (int i = 1; i <= n; i++) a[i] = cin.nextInt();
            for (int i = 1; i <= n; i++) {
                if (i >= 1) {
                    f[i] = 3 + f[i - 1];
                }
                if (i >= 2) {
                    if (a[i] > a[i - 1] && a[i] - a[i - 1] <= 30) f[i] = Math.min(f[i], f[i - 2] + 2);
                }
                if (i >= 3) {
                    int min = Math.min(a[i] - a[i - 1], a[i - 1] - a[i - 2]);
                    int max = Math.max(a[i] - a[i - 1], a[i - 1] - a[i - 2]);
                    if (0 < min && min <= 10 && max <= 20) f[i] = Math.min(f[i], f[i - 3] + 1);
                }
                if (i >= 4) {
                    if (ok(a[i - 3], a[i - 2], a[i - 1], a[i])) f[i] = Math.min(f[i], f[i - 4]);
                }
            }

            cout.printf("Case #%d: %d%n", _case, f[n]);
        }

        cin.close();
        cout.close();
    }
}
