import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GettingTheDigits {

    static int n;
    static String[][] a;


    static void print(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++)
                System.out.print(a[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//    Scanner cin = new Scanner(new File("A-small-practice.in"));
//    PrintStream cout = new PrintStream("A-small-practice.out");
        Scanner cin = new Scanner(new File("A-large-practice.in"));
        PrintStream cout = new PrintStream("A-large-practice.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;

        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;
            StringBuilder ans = new StringBuilder();

            final String[] digits = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
            double[][] a = new double[26][11];
            for (char ch : cin.next().toCharArray()) a[ch - 'A'][10]++;
            for (int i = 0; i < 10; i++)
                for (char ch : digits[i].toCharArray())
                    a[ch - 'A'][i]++;

//            print(a);
            for (int i = 0; i < 10; i++) {
                int row = i;
                for (int j = i; j < 26; j++)
                    if (Math.abs(a[j][i]) > Math.abs(a[row][i])) row = j;

                double[] tmp = a[row];
                a[row] = a[i];
                a[i] = tmp;

                for (int j = 10; j >= i; j--) a[i][j] /= a[i][i];
                for (int ii = i + 1; ii < 26; ii++)
                    for (int j = 10; j >= i; j--)
                        a[ii][j] -= a[i][j] * a[ii][i];

//                System.out.println();
//                print(a);
            }
            for (int ii = 8; ii >= 0; ii--) {
                for (int j = ii + 1; j < 10; j++)
                    a[ii][10] -= a[ii][j] * a[j][10];
            }

            for (int i = 0; i < 10; i++)
                for (int j = 0; j < Math.round(a[i][10]); j++)
                    ans.append(i);

            cout.printf("Case #%d: %s%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
