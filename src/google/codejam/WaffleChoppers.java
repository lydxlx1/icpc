package google.codejam;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaffleChoppers {

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

            int r = cin.nextInt();
            int c = cin.nextInt();
            int h = cin.nextInt();
            int v = cin.nextInt();

            char[][] a = new char[r][];
            for (int i = 0; i < r; i++) {
                a[i] = cin.next().toCharArray();
            }


            String ans = isok(a, r, c, h, v) ? "POSSIBLE" : "IMPOSSIBLE";
            cout.printf("Case #%d: %s%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

    static boolean isok(char[][] a, int r, int c, int h, int v) {
        int[] rowCnt = new int[r];
        int[] colCnt = new int[c];
        int total = 0;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (a[i][j] == '@') {
                    total++;
                    rowCnt[i]++;
                    colCnt[j]++;
                }
        if (total % (h + 1) != 0 || total % (v + 1) != 0) return false;


        int each = total / (h + 1) / (v + 1);
        int i = 0;
        int cnt = 0;

        List<Integer> hCuts = new ArrayList<>();
        List<Integer> vCuts = new ArrayList<>();


        int round = h + 1;
        while (round > 0) {
            cnt += rowCnt[i];
            if (cnt < total / (h + 1)) {
                if (i >= r) return false;
                i++;
                continue;
            } else if (cnt == total / (h + 1)) {
                hCuts.add(i);
                i++;
                round--;
                cnt = 0;
            } else {
                return false;
            }
        }

//        System.out.println(hCuts);

        round = v + 1;
        cnt = 0;
        i = 0;
        while (round > 0) {
            cnt += colCnt[i];
            if (cnt < total / (v + 1)) {
                if (i >= c) return false;
                i++;
                continue;
            } else if (cnt == total / (v + 1)) {
                vCuts.add(i);
                i++;
                round--;
                cnt = 0;
            } else {
                return false;
            }
        }

//        System.out.println(vCuts);

        for (i = 0; i < hCuts.size(); i++)
            for (int j = 0; j < vCuts.size(); j++) {
                cnt = 0;
                for (int ii = (i == 0 ? 0 : hCuts.get(i - 1) + 1); ii <= hCuts.get(i); ii++)
                    for (int jj = (j == 0 ? 0 : vCuts.get(j - 1) + 1); jj <= vCuts.get(j); jj++) {
                        if (a[ii][jj] == '@') cnt++;
                    }
                if (cnt != each) return false;
            }
        return true;
    }
}
