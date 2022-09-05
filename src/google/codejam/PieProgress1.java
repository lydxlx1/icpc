package google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class PieProgress1 {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("A.in"));
        PrintStream cout = new PrintStream("A.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int n = cin.nextInt();
            int m = cin.nextInt();
            long[][] c = new long[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++)
                    c[i][j] = cin.nextInt();
                Arrays.sort(c[i]);
                for (int j = 1; j <= m; j++)
                    c[i][j] += j * j - (j - 1) * (j - 1); // adjust the price accordingly
            }

            PriorityQueue<List<Integer>> queue = new PriorityQueue<>(Comparator.comparing(list -> {
                int day = list.get(0);
                int pie = list.get(1);
                return c[day][pie];
            }));
            queue.add(Arrays.asList(1, 1));

            int ans = 0;
            int globalDay = 1;
            for (int i = 0; i < n; i++) {
                int day = queue.peek().get(0);
                int pie = queue.poll().get(1);
                ans += c[day][pie];

                if (pie + 1 <= m) queue.add(Arrays.asList(day, pie + 1));
                if (globalDay + 1 <= n) queue.add(Arrays.asList(++globalDay, 1));
            }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
