package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class AlienRhyme {

    static class Node {
        Node[] child = new Node[26];
        int[] cnt = new int[26];
    }

    static int ans = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner cin = new Scanner(System.in);
        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            Node root = new Node();
            int n = cin.nextInt();
            for (int i = 0; i < n; i++) {
                String s = cin.next();
                insert(root, (new StringBuilder(s)).reverse().toString());
            }

            ans = 0;
            dfs(root);
            System.out.printf("Case #%d: %d%n", _case, ans);
        }

        cin.close();
        cout.close();
    }

    static void insert(Node root, String s) {
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'A';
            if (root.child[ch] == null) {
                root.child[ch] = new Node();
            }
            root.cnt[ch]++;
            root = root.child[ch];
        }
    }

    static int dfs(Node root) {
        if (root == null) {
            return 0;
        }
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            int tmp = dfs(root.child[i]);
            root.cnt[i] -= tmp;
            diff += tmp;
            if (root.cnt[i] >= 2) {
                ans += 2;
                diff += 2;
            }
        }
        return diff;
    }
}
