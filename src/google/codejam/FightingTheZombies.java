package google.codejam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FightingTheZombies {

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static long r;
    static Point[] a;
    static Point[] buffer = new Point[55];

    static Map<Long, Integer> map;

    private static int easyVersion(long mask) {
        if (map.containsKey(mask)) return map.get(mask);

        int len = 0;
        for (int i = 0; i < n; i++)
            if (((1L << i) & mask) != 0) {
                buffer[len++] = a[i];
            }

        int ans = 0;

        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++) {
                long x = buffer[i].x;
                long y = buffer[j].y;
                int cnt = 0;
                for (int k = 0; k < len; k++) {
                    Point p = buffer[k];
                    if (p.x >= x && p.x - x <= r && p.y >= y && p.y - y <= r) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }

        map.put(mask, ans);
        return ans;
    }


    private static BigInteger distSquare(Point a, Point b) {
        BigInteger x = BigInteger.valueOf(a.x - b.x);
        BigInteger y = BigInteger.valueOf(a.y - b.y);
        return x.multiply(x).add(y.multiply(y));
    }

    private static boolean collinear(Point a, Point b, Point c) {
        long x1 = b.x - a.x;
        long y1 = b.y - a.y;
        long x2 = c.x - a.x;
        long y2 = c.y - a.y;
        return Long.signum(x1 * y2 - x2 * y1) == 0;
    }

    private static BigInteger det(BigInteger A11, BigInteger A12, BigInteger A21, BigInteger A22) {
        return A11.multiply(A22).subtract(A21.multiply(A12));
    }

    private static BigInteger[] genRow(Point a, Point d) {
        BigInteger cb = BigInteger.valueOf(a.x - d.x);
        BigInteger wb = BigInteger.valueOf(a.y - d.y);
        return new BigInteger[]{cb, wb, cb.multiply(cb).add(wb.multiply(wb))};
    }

    private static boolean inCircle(Point a, Point b, Point c, Point d) {
        BigInteger[][] A = {
                genRow(a, d),
                genRow(b, d),
                genRow(c, d),
        };

        BigInteger one = A[0][0].multiply(det(A[1][1], A[1][2], A[2][1], A[2][2]));
        BigInteger two = A[0][1].multiply(det(A[1][0], A[1][2], A[2][0], A[2][2]));
        BigInteger three = A[0][2].multiply(det(A[1][0], A[1][1], A[2][0], A[2][1]));
        BigInteger res = one.subtract(two).add(three);
        return res.signum() >= 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("C-small-attempt0.in"));
//        PrintStream cout = new PrintStream("C-small-attempt0.out");
        Scanner cin = new Scanner(new File("B.in"));
        PrintStream cout = new PrintStream("B.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;
            System.out.println(_case);

            map = new HashMap<>();
            int ans = 0;
            n = cin.nextInt();
            r = cin.nextInt() * 2;
            a = new Point[n];
            for (int i = 0; i < n; i++) {
                a[i] = new Point(cin.nextInt() * 2, cin.nextInt() * 2);
            }

            long globalMask = (1L << n) - 1;

            // A single point is in the circle
            for (int i = 0; i < n; i++)
                ans = Math.max(ans, easyVersion(1L << i) + easyVersion(globalMask - (1L << i)));

            // Two points are on a line
            // Enumerate the two extreme points
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++) {
                    Point center = new Point((a[i].x + a[j].x) / 2, (a[i].y + a[j].y) / 2);

                    BigInteger radiusSquare = distSquare(a[i], center);
                    long mask = globalMask ^ (1L << i) ^ (1L << j);
                    for (int k = 0; k < n; k++)
                        if (k != i && k != j) {
                            BigInteger dist = distSquare(a[k], center);
                            int cmp = dist.compareTo(radiusSquare);
                            if (cmp <= 0) {
                                mask = mask ^ (1L << k);
                            }
                        }

                    ans = Math.max(ans, easyVersion(mask) + easyVersion(globalMask ^ mask));
                }

            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    for (int k = j + 1; k < n; k++) {
                        if (collinear(a[i], a[j], a[k])) continue;

                        long mask = globalMask ^ (1L << i) ^ (1L << j) ^ (1L << k);
                        for (int l = 0; l < n; l++)
                            if (l != i && l != j && l != k && inCircle(a[i], a[j], a[k], a[l])) {
                                mask = mask ^ (1L << l);
                            }

                        ans = Math.max(ans, easyVersion(mask) + easyVersion(globalMask ^ mask));
                    }

            cout.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
