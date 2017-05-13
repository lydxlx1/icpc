import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Yachtzee {

    static double calc(long[] a, int x) {
        long periodicSum = 0;
        long periodicArea = 0;
        for (long each : a) {
            periodicSum += each;
            periodicArea += each * each;
        }

        long total = x / periodicSum * periodicArea;
        x %= periodicSum;

        for (int i = 0; x > 0; i++) {
            long tmp = Math.min(x, a[i]);
            total += tmp * tmp;
            x -= tmp;
        }

        return total / 2.0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader cin = new BufferedReader(new FileReader("3.in"));
        PrintStream cout = new PrintStream("3.out");
        //		Scanner cin = new Scanner(new File("A-large-practice.in"));
        //		PrintStream cout = new PrintStream("A-large-practice.out");
        //    Scanner cin = new Scanner(System.in);
        //    PrintStream cout = System.out;

        int _case = 0;
        StringTokenizer token = new StringTokenizer(cin.readLine());
        for (int T = Integer.parseInt(token.nextToken().trim()); T > 0; T--) {
            _case++;

            token = new StringTokenizer(cin.readLine());
            int N = Integer.parseInt(token.nextToken());
            int A = Integer.parseInt(token.nextToken());
            int B = Integer.parseInt(token.nextToken());
            long[] a = new long[N];
            token = new StringTokenizer(cin.readLine());
            for (int i = 0; i < N; i++) a[i] = Integer.parseInt(token.nextToken());

            double ans = (calc(a, B) - calc(a, A)) / (B - A);
            cout.printf("Case #%d: %.12f%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
