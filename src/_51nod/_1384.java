package _51nod;

import java.io.*;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class _1384 {


    static boolean nextPermutation(char[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i - 1] >= a[i]) continue;

            for (int j = a.length - 1; j >= i; j--) {
                if (a[j] <= a[i - 1]) continue;

                char tmp = a[i - 1];
                a[i - 1] = a[j];
                a[j] = tmp;
                Arrays.sort(a, i, a.length); // too lazy to write a reverse...
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] a = Reader.next().toCharArray();
        StringJoiner joiner = new StringJoiner("\n");

        Arrays.sort(a);
        do {
            joiner.add(String.valueOf(a));
        } while (nextPermutation(a));
        System.out.println(joiner.toString());

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
