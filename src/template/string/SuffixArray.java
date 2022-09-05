package template.string;

import java.util.Arrays;

/**
 * Java implementation
 * 1. lcp(x, y)表示是的s[x..], s[y..]的lcp
 * 2. index[x]表示的是rank是x的suffix的index
 * 比如如果需要求rank是0的和rank是1的两个suffix的lcp，就应该写成
 * sa.lcp(sa.index[0], sa.index[1])
 *
 *
 *  後綴數組，O(n log n)時間，O(n log n)空間。注意一下幾點：
 *  1）如果需要O(n)空間，那麽對P數組進行滾動即可，但是這樣一來就無法求lcp了。
 *  2）rank[i]表示以第i個字符開始的後綴的大小排名。
 *  3）真正的後綴數組SA，其實就是L數組，L[i].index就是排名為i的後綴。
 *    1] 排序用了2次radixsort，我手寫了雙向循環鏈表。
 *    2] 其中有一個優化，如果某次排序完之後，所有人的rank都不相同，那麽此時的rank[]就是最終的rank[]。
 *  !!! 一般情況下，radixSort裏面這個up的大小是足夠的，但是需要對於數組是整數以及做了差就要仔細考慮了。
 **/
public class SuffixArray {
    private char str[];
    private int MAX_LOG = 30;
    private int n, nr0[], nr1[], rank[][], index[], step, cnt;

    private int M;
    private int minusOneHead;
    private int minusOneTail;
    private int bucketHead[];
    private int bucketTail[];
    private int pointTo[];
    private int next[];
    private int globalPtr;

    public SuffixArray(String s) {
        str = s.toCharArray();
        n = str.length;
        nr0 = new int[n];
        nr1 = new int[n];
        index = new int[n];
        rank = new int[MAX_LOG][n];
        M = Math.max(128, n);
        bucketHead = new int[M];
        bucketTail = new int[M];
        pointTo = new int[M];
        next = new int[M];

        for (int i = 0; i < n; i++)
            rank[0][i] = str[i];

        boolean needSort = true;
        for (step = cnt = 1; (cnt >> 1) < n; step++, cnt <<= 1) {
            if (!needSort) {
                for (int i = 0; i < n; i++)
                    rank[step][i] = rank[step - 1][i];
                continue;
            }
            for (int i = 0; i < n; i++) {
                nr0[i] = rank[step - 1][i];
                nr1[i] = i + cnt < n ? rank[step - 1][i + cnt] : -1;
                index[i] = i;
            }

            radixSort1();
            radixSort0();

            needSort = false;
            for (int i = 0; i < n; i++) {
                if (i > 0 && nr0[index[i]] == nr0[index[i - 1]] && nr1[index[i]] == nr1[index[i - 1]]) {
                    rank[step][index[i]] = rank[step][index[i - 1]];
                    needSort = true;
                } else rank[step][index[i]] = i;
            }
        }
    }

    private void radixSort0() {
        globalPtr = 0;
        Arrays.fill(bucketHead, -1);
        Arrays.fill(next, -1);

        for (int i = 0; i < n; i++) {
            int value = nr0[index[i]];
            if (bucketHead[value] == -1) bucketHead[value] = bucketTail[value] = globalPtr;
            else bucketTail[value] = next[bucketTail[value]] = globalPtr;
            pointTo[globalPtr++] = index[i];
        }

        int ptr = 0;
        for (int i = 0; i < M; i++)
            for (int j = bucketHead[i]; j != -1; j = next[j])
                index[ptr++] = pointTo[j];
    }

    private void radixSort1() {
        globalPtr = 0;
        Arrays.fill(bucketHead, -1);
        Arrays.fill(next, -1);
        minusOneHead = minusOneTail = -1;

        for (int i = 0; i < n; i++) {
            int value = nr1[index[i]];
            if (value == -1) {
                if (minusOneHead == -1) minusOneHead = minusOneTail = globalPtr;
                else minusOneTail = next[minusOneTail] = globalPtr;
            } else {
                if (bucketHead[value] == -1) bucketHead[value] = bucketTail[value] = globalPtr;
                else bucketTail[value] = next[bucketTail[value]] = globalPtr;
            }
            pointTo[globalPtr++] = index[i];
        }

        int ptr = 0;
        for (int j = minusOneHead; j != -1; j = next[j])
            index[ptr++] = pointTo[j];
        for (int i = 0; i < M; i++)
            for (int j = bucketHead[i]; j != -1; j = next[j])
                index[ptr++] = pointTo[j];
    }

    public int lcp(int x, int y) {
        int k, ret = 0;
        if (x == y) return n - x;
        for (k = step - 1; k >= 0 && x < n && y < n; k--)
            if (rank[k][x] == rank[k][y]) {
                x += 1 << k;
                y += 1 << k;
                ret += 1 << k;
            }
        return ret;
    }
}