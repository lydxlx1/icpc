import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class BFFs {

    static int n;
    static boolean[][] g;
    static int[] f;
    static int[] dist;
    static int[] a;


    static int dfs(int current, int start, int depth) {
        dist[current] = depth;
        if (current == start) {
            return dist[current];
        } else if (dist[f[current]] != -1) return -1;
        else return dfs(f[current], start, depth + 1);
    }

    static int dfs(int u, int badass) {
        int max = 0;
        for (int v = 0; v < n; v++)
            if (g[v][u] && v != badass) max = Math.max(max, dfs(v, badass) + 1);
        return max;
    }

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
            StringBuilder ans = new StringBuilder();

            n = cin.nextInt();
            g = new boolean[n][n];
            a = new int[n];
            f = new int[n];
            for (int i = 0; i < n; i++) {
                int u = i;
                int v = cin.nextInt() - 1;
                f[i] = v;
                g[u][v] = true;
            }

            int max = 0;
            dist = new int[n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist, -1);
                max = Math.max(max, dfs(f[i], i, 1));
            }

            int max1 = 0;
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    if (g[i][j] && g[j][i]) {
                        max1 += dfs(i, j) + dfs(j, i) + 2;
                    }

            ans.append(Math.max(max, max1));


            cout.printf("Case #%d: %s%n", _case, ans.toString());
        }

        cin.close();
        cout.close();
    }
}
