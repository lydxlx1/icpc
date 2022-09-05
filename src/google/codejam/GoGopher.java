package google.codejam;

import java.util.Scanner;

public class GoGopher {

    public static void main(String[] args) throws Exception {
//        Scanner cin = new Scanner(new File("B-small-attempt0.in"));
//        PrintStream cout = new PrintStream("B-small-attempt0.out");
//        Scanner cin = new Scanner(new File("B-large.in"));
//        PrintStream cout = new PrintStream("B-large.out");
        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int total = cin.nextInt();

            int col = Math.max(3, (total + 2) / 3);
            boolean[][] board = new boolean[4][col + 2];
            int[] cnt = new int[col + 2];


            System.out.println(2 + " " + 2);
            while (true) {
                int x = cin.nextInt(), y = cin.nextInt();
                if (x == 0 && y == 0) {
                    break;
                }

                if (!board[x][y]) {
                    cnt[y - 1]++;
                    cnt[y]++;
                    cnt[y + 1]++;
                    board[x][y] = true;
                }

                int best = 2;
                for (int i = 2; i <= col - 1; i++) {
                    if (cnt[i] < cnt[best]) {
                        best = i;
                    }
                }

                System.out.println(2 + " " + best);
//                System.err.println(Arrays.toString(cnt));
            }
//            cout.printf("Case #%d: %s%n", _case, ans);
        }

//        cout.close();
    }
}
