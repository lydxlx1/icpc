package template.number_theory;

import java.util.ArrayList;
import java.util.List;

public class EulerSieve {
    public static List<Integer> eulerSieve(int maxN) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[maxN + 1];
        for (int i = 2; i < isPrime.length; i++) isPrime[i] = true;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) primes.add(i);
            for (int prime : primes) {
                if (i * prime >= isPrime.length) break;
                isPrime[i * prime] = false;
                if (i % prime == 0) break;
            }
        }
        return primes;
    }
}


