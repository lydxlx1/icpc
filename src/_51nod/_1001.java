package _51nod;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1001 {

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Reader.nextInt();
        int n = Reader.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Reader.nextInt();
        Arrays.sort(a);

        boolean noSolution = true;
        for (int i = 0, j = n - 1; i < j; )
            if (a[i] + a[j] == k) {
                System.out.println(a[i] + " " + a[j]);
                i++;
                j--;
                noSolution = false;
            } else if (a[i] + a[j] < k)
                i++;
            else
                j--;

        if (noSolution)
            System.out.println("No Solution");

        cout.close();
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
