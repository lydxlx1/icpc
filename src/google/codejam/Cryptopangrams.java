package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Cryptopangrams {

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

            cin.next(); // Skip n
            int L = cin.nextInt();
            BigInteger[] a = new BigInteger[L];
            for (int i = 0; i < L; i++) {
                a[i] = new BigInteger(cin.next());
            }

            BigInteger[] primes = new BigInteger[L + 1];
            for (int i = 0; i < L - 1; i++) {
                if (!a[i].equals(a[i + 1])) {
                    primes[i + 1] = a[i].gcd(a[i + 1]);
                    primes[i] = a[i].divide(primes[i + 1]);
                    break;
                }
            }

            for (int i = 0; i < L + 1; i++) {
                if (primes[i] != null) {
                    for (int j = i + 1; j < L + 1; j++) {
                        primes[j] = a[j - 1].divide(primes[j - 1]);
                    }
                    for (int j = i - 1; j >= 0; j--) {
                        primes[j] = a[j].divide(primes[j + 1]);
                    }
                }
            }


            BigInteger[] sortedAndDistinct = Stream.of(primes).distinct().sorted().toArray(BigInteger[]::new);
//            System.out.println(sortedAndDistinct.length);
            Map<BigInteger, Integer> index = new HashMap<>();
            for (int i = 0; i < sortedAndDistinct.length; i++) {
                index.put(sortedAndDistinct[i], i);
            }

//            System.out.println(Arrays.toString(primes));
            StringBuilder builder = new StringBuilder();
            for (BigInteger p : primes) {
                builder.append((char) (index.get(p) + 'A'));
            }

            cout.printf("Case #%d: %s%n", _case, builder.toString());
        }

        cin.close();
        cout.close();
    }
}

