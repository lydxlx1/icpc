package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringJoiner;

public class DatBae {

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
            int b = cin.nextInt();
            int f = cin.nextInt();

            int[] res = new int[n - b];
            for (int i = 0; i < f; i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    builder.append(((1 << i) & j) == 0 ? 0 : 1);
                }
                System.out.println(builder.toString());

                String response = cin.next();
                for (int j = 0; j < response.length(); j++) {
                    if (response.charAt(j) == '1') {
                        res[j] += 1 << i;
                    }
                }
            }

            StringJoiner joiner = new StringJoiner(" ");
            for (int i = 0, j = 0; i < n; i++) {
                if (j < res.length && (i % (1 << f)) == res[j]) {
                    j++;
                } else {
                    joiner.add("" + i);
                }
            }
            System.out.println(joiner.toString());

            cin.nextInt(); // read verdict
        }

        cin.close();
        cout.close();
    }
}

