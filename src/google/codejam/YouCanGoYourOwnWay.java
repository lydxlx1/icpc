package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class YouCanGoYourOwnWay {

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

            int n = cin.nextInt();
            String s = cin.next();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'E') {
                    builder.append('S');
                } else {
                    builder.append('E');
                }
            }


            cout.printf("Case #%d: %s%n", _case, builder.toString());
        }

        cin.close();
        cout.close();
    }
}

