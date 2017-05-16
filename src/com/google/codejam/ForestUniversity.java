package com.google.codejam;

import javax.xml.transform.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class ForestUniversity {

    static Random rand = new Random();
    static int N, M;
    static List<Integer>[] tree;
    static char[] letters;

    static int[] permutation(int n) {
        int[] permutation = new int[n];
        for (int i = 0; i < n; i++) permutation[i] = i;
        for (int i = n - 1; i > 0; i--) {
            int swapIndex = rand.nextInt(i + 1);
            int tmp = permutation[i];
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = tmp;
        }
        return permutation;
    }

    static String dfs(int u, int parent) {
        List<String> candString = new ArrayList<>();
        int totalLength = 0;

        for (int v : tree[u])
            if (v != parent) {
                String cand = dfs(v, u);
                candString.add(cand);
                totalLength += cand.length();
            }

        if (totalLength == 0) return "" + letters[u];
        else {
            int[] permute = permutation(totalLength);

            int beginIndex = 0;
            for (String str : candString) {
                Arrays.sort(permute, beginIndex, beginIndex + str.length());
                beginIndex += str.length();
            }

            char[] joinedString = new char[totalLength];
            beginIndex = 0;
            for (String str : candString)
                for (int i = 0; i < str.length(); i++)
                    joinedString[permute[beginIndex++]] = str.charAt(i);
            return (u == 0 ? "" : "" + letters[u]) + String.copyValueOf(joinedString);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(new File("B-small-practice.in"));
        PrintStream cout = new PrintStream("B-small-practice.out");
//        Scanner cin = new Scanner(new File("A-large.in"));
//        PrintStream cout = new PrintStream("A-large.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;
            System.out.println(_case);

            StringBuilder ans = new StringBuilder();

            N = cin.nextInt();
            tree = new List[N + 1];
            for (int i = 0; i <= N; i++) tree[i] = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                int parent = cin.nextInt();
                tree[i].add(parent);
                tree[parent].add(i);
            }
            letters = (" " + cin.next()).toCharArray(); // append a dummy char for the dummy root

            M = cin.nextInt();
            for (int i = 0; i < M; i++) {
                String test = cin.next();
                int totalTry = 5000, hit = 0;
                for (int j = 0; j < totalTry; j++) {
                    if (dfs(0, -1).contains(test)) hit++;
                }

                ans.append(String.format("%.10f", 1.0 * hit / totalTry));
                if (i < M - 1) ans.append(" ");
            }

            cout.printf("Case #%d: %s%n", _case, ans.toString());
        }

        cin.close();
        cout.close();
    }
}
