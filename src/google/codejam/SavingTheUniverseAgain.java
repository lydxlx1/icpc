package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class SavingTheUniverseAgain {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("B-small-attempt0.in"));
//        PrintStream cout = new PrintStream("B-small-attempt0.out");
//        Scanner cin = new Scanner(new File("B-large.in"));
//        PrintStream cout = new PrintStream("B-large.out");
        Scanner cin = new Scanner(System.in);
        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            int D = cin.nextInt();
            char[] s = cin.next().toCharArray();

//            System.out.println(eval(s) + " " + String.copyValueOf(s));

            int round = 0;
            while (canMove(s) && eval(s) > D) {
                for (int i = s.length - 2; i >= 0; i--) {
                    if (s[i] == 'C' && s[i + 1] == 'S') {
                        s[i] = 'S';
                        s[i + 1] = 'C';
                        round++;
                        break;
                    }
                }
//                System.out.println(eval(s) + " " + String.copyValueOf(s));
            }


            String ans = eval(s) <= D ? "" + round : "IMPOSSIBLE";
            cout.printf("Case #%d: %s%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

    static long eval(char[] s) {
        long damage = 0;
        long d = 1;
        for (char ch : s) {
            if (ch == 'C') {
                d *= 2;
            } else {
                damage += d;
            }
        }
        return damage;
    }

    static boolean canMove(char[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i] == 'C' && s[i + 1] == 'S') {
                return true;
            }
        }
        return false;
    }
}
