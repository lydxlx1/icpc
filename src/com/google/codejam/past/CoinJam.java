import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class CoinJam {

    public static long getFactor(BigInteger x) {
        for (long i = 2; i < Long.MAX_VALUE / 2; i++)
            if (x.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO)) return i;
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("C-large.in"));
        PrintStream cout = new PrintStream("C-large.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        int N = 32;
        int J = 500;
        for (int hehe = 0; hehe < (1 << (N / 2 - 1)) && cnt < J; hehe++) {
            if (Integer.bitCount(hehe) % 3 != 2) continue;

            // generating the number
            int number = 1 | (1 << 31);
            for (int odd = 1, even = 2, i = 0; i < N / 2 - 1; odd += 2, even += 2, i++) {
                if ((hehe & (1 << i)) != 0) {
                    number = number | (1 << odd);
                    number = number | (1 << even);
                }
            }
//            System.out.println("Generating number " + Integer.toBinaryString(number));

            // Double validation
            if (Integer.bitCount(number) % 6 != 0) continue;
            int evenCnt = 0, oddCnt = 0;
            for (int i = 0; i < 32; i += 2) if ((number & (1 << i)) != 0) evenCnt++;
            for (int i = 1; i < 32; i += 2) if ((number & (1 << i)) != 0) oddCnt++;
            if (evenCnt != oddCnt) continue;
//            System.out.println("Validating successfully " + Integer.toBinaryString(number));

            // must be a valid candidate
            StringBuilder line = new StringBuilder();
            line.append(Integer.toBinaryString(number));

            for (int i = 2; i <= 10; i++) {
                BigInteger value = new BigInteger(Integer.toBinaryString(number), i);
                line.append(" ");
                line.append(getFactor(value));
            }
//            System.out.println(line.toString());
            ans.append(line.toString());
            ans.append("\n");
            cnt++;
        }
        cout.printf("Case #1:\n%s", ans.toString());

        cin.close();
        cout.close();
    }
}
