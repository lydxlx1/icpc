import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class BathroomStalls {

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("C-large.in"));
        PrintStream cout = new PrintStream("C-large.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            long n = cin.nextLong();
            long k = cin.nextLong();
            TreeMap<Long, Long> map = new TreeMap<>();
            map.put(n, 1L);
            while (map.lastEntry().getValue() < k) {
                long num = map.lastKey();
                long cnt = map.get(num);
                map.remove(num);
                k -= cnt;

                long cb = (num - 1) / 2;
                long wb = num - 1 - cb;
                if (cb > 0) {
                    map.putIfAbsent(cb, 0L);
                    map.put(cb, map.get(cb) + cnt);
                }
                if (wb > 0) {
                    map.putIfAbsent(wb, 0L);
                    map.put(wb, map.get(wb) + cnt);
                }
            }

            long num = map.lastKey();
            long cb = (num - 1) / 2;
            long wb = num - 1 - cb;
            cout.printf("Case #%d: %d %d%n", _case, wb, cb);
        }

        cin.close();
        cout.close();
    }
}
