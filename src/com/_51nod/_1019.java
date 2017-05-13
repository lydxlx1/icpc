package com._51nod;

import java.io.*;
import java.util.StringTokenizer;

public class _1019 {

    static int[] a, tmp;
    static int n;
    static long inv = 0;

    static void mergeSort(int l, int r) {
        if (l >= r) return;
        int mid = (l + r) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);

        int i = l, j = mid + 1, k = l;
        while (i <= mid && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                inv += mid - i + 1;
                tmp[k++] = a[j++];
            }
        }
        while (i <= mid) tmp[k++] = a[i++];
        while (j <= r) tmp[k++] = a[j++];

        System.arraycopy(tmp, l, a, l, r - l + 1);
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Reader.nextInt();
        a = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Reader.nextInt();
        mergeSort(0, n - 1);
        System.out.println(inv);

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
