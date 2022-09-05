package google.codejam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TroubleSort {

    public static void main(String[] args) throws Exception {
//        Scanner cin = new Scanner(new File("B-small-attempt0.in"));
//        PrintStream cout = new PrintStream("B-small-attempt0.out");
//        Scanner cin = new Scanner(new File("B-large.in"));
//        PrintStream cout = new PrintStream("B-large.out");
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        PrintStream cout = System.out;

        int _case = 0;
        for (int T = Integer.parseInt(cin.readLine().trim()); T > 0; T--) {
            _case++;

            int n = Integer.parseInt(cin.readLine().trim());
            int[] a = new int[n];
            StringTokenizer token = new StringTokenizer(cin.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(token.nextToken());
            }

            List<Integer> even = new ArrayList<>(n);
            List<Integer> odd = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    even.add(a[i]);
                } else {
                    odd.add(a[i]);
                }
            }
            Collections.sort(odd);
            Collections.sort(even);

            for (int i = 0, j = 0; i < even.size(); i++, j += 2) {
                a[j] = even.get(i);
            }
            for (int i = 0, j = 1; i < odd.size(); i++, j += 2) {
                a[j] = odd.get(i);
            }


            String ans = "OK";
            for (int i = 0; i < n - 1; i++) {
                if (a[i] > a[i + 1]) {
                    ans = "" + i;
                    break;
                }
            }

            cout.printf("Case #%d: %s%n", _case, ans);
        }

        cout.close();
    }
}
