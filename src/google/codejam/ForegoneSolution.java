package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class ForegoneSolution {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
//        Scanner cin = new Scanner(new File("A.in"));
//        PrintStream cout = new PrintStream("A.out");
        Scanner cin = new Scanner(System.in);
        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;


            String s = cin.next();
            StringBuilder a = new StringBuilder();
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                int d = s.charAt(i) - '0';
                if (d == 4) {
                    a.append(1);
                    b.append(3);
                } else {
                    a.append(d);
                    b.append(0);
                }
            }

            BigInteger first = get(a.toString());
            BigInteger second = get(b.toString());
            cout.printf("Case #%d: %d %d%n", _case, first, second);
        }

        cin.close();
        cout.close();
    }

    static BigInteger get(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                return new BigInteger(s.substring(i));
            }
        }
        return BigInteger.ZERO;
    }
}
