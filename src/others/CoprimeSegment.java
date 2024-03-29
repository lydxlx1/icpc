package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CoprimeSegment {

    static {
        Reader.init(System.in);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    public static void main(String[] args) throws Exception {
        int n = Reader.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(Reader.next());
        }

        int ans = dfs(a, new long[n], 0, n - 1);
        System.out.println(ans < (1 << 29) ? ans : -1);
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
