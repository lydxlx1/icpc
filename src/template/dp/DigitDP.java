package template.dp;

import java.util.Arrays;

public class DigitDP {

    /*
     * Count the occurrence of the given digit from 1 to mask
     * e.g.
     * 
     * mask = 10
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     * 10
     * 
     * 1 appears twice
     * 0 appears once
     * 2 - 9 appear once
     */
    static class DP {
        static final int MAX_LEN = 11;
        static final long[] POW = new long[20];
        static {
            POW[0] = 1;
            for (int i = 1; i < POW.length; i++)
                POW[i] = POW[i - 1] * 10;
        }
        long mask;
        int digit;
        long[][][] f = new long[MAX_LEN + 1][2][2];

        public DP(long mask, int digit) {
            this.mask = mask;
            this.digit = digit;
            for (long[][] i : f)
                for (long[] j : i)
                    Arrays.fill(j, -1);
        }

        public long apply() {
            return F(MAX_LEN, 0, 0);
        }

        private long F(int len, int state, int start) {
            if (len < 0)
                return 0;
            if (f[len][state][start] != -1)
                return f[len][state][start];

            long ans = 0;
            int up = state == 1 ? 9 : and(len);
            for (int i = 0; i <= up; i++) {
                int newState = state == 1 || i < up ? 1 : 0;
                int newStart = start == 1 || i > 0 ? 1 : 0;
                if (i == digit && newStart == 1) {
                    if (newState == 1)
                        ans += POW[len];
                    else
                        ans += mask % POW[len] + 1;
                }
                ans += F(len - 1, newState, newStart);
            }
            return f[len][state][start] = ans;
        }

        private int and(int len) {
            return (int) (mask / POW[len] % 10);
        }
    }
}
