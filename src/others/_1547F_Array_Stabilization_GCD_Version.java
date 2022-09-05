package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class _1547F_Array_Stabilization_GCD_Version {

    static {
        Reader.init(System.in);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    static int[][] st;
    static int[] log; // largest 2^(log[i]) <= i

    static int f(int[] a, int l, int r) {
        if (l == r) {
            return a[l];
        }
        int L = log[r - l + 1];
        return gcd(st[l][L], st[r - (1 << L) + 1][L]);
    }


    public static void main(String[] args) throws Exception {
        int T = Reader.nextInt();
        while (T-- > 0) {
            int n = Reader.nextInt();

            int[] a = new int[2 * n];
            for (int i = 0; i < n; i++) {
                a[i] = a[i + n] = Reader.nextInt();
            }

            st = new int[2 * n][20];
            for (int j = 0; j < 20; j++) {
                for (int i = 0; i < 2 * n; i++) {
                    if (j == 0) {
                        st[i][j] = a[i];
                    } else {
                        if (i + (1 << (j - 1)) < 2 * n) {
                            st[i][j] = gcd(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                        } else {
                            st[i][j] = st[i][j - 1];
                        }
                    }
                }
            }
            log = new int[2 * n];
            for (int i = 2; i < 2 * n; i++) {
                while ((1 << (log[i])) <= i) {
                    log[i]++;
                }
                log[i]--;
            }

            if (IntStream.of(a).distinct().count() == 1) {
                System.out.println(0);
            } else {
                int left = 1, right = n;
                while (left + 1 < right) {
                    int mid = (left + right) / 2;

                    boolean ok = true;
                    int g = f(a, 0, mid - 1);
                    for (int i = 1; i < n; i++) {
                        if (f(a, i, i + mid - 1) != g) {
                            ok = false;
                            break;
                        }
                    }

                    if (ok) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                }
                System.out.println(right - 1);
            }
        }
    }

    static int dfs(long[] a, long[] tmp, int l, int r) {
        if (l == r) {
            return a[l] == 1 ? 1 : 1 << 29;
        }

        int mid = (l + r) / 2;
        int ans = Math.min(dfs(a, tmp, l, mid), dfs(a, tmp, mid + 1, r));

        tmp[mid] = a[mid];
        tmp[mid + 1] = a[mid + 1];
        for (int i = mid - 1; i >= l; i--) {
            tmp[i] = gcd(tmp[i + 1], a[i]);
        }
        for (int i = mid + 2; i <= r; i++) {
            tmp[i] = gcd(tmp[i - 1], a[i]);
        }

        for (int i = mid, j = r; i >= l && j >= mid + 1; i--) {
            while (j - 1 >= mid + 1 && gcd(tmp[i], tmp[j - 1]) == 1) {
                j--;
            }
            if (gcd(tmp[i], tmp[j]) == 1) {
                ans = Math.min(ans, j - i + 1);
//                if (ans == 2) {
//                    System.out.println(i + " " + j + " " + Arrays.toString(tmp));
//                }
            }
        }
        return ans;
    }


    /**
     * Class for buffered reading int and double values
     */
    static class Reader {
        static BufferedReader reader;
        static StringTokenizer tokenizer;

        /**
         * call this method to initialize reader for InputStream
         */
        static void init(InputStream input) {
            reader = new BufferedReader(new InputStreamReader(input));
            tokenizer = new StringTokenizer("");
        }

        /**
         * get next word
         */
        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                // TODO add check for eof if necessary
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
