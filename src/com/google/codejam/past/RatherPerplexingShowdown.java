import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class RatherPerplexingShowdown {

    static int n, r, p, s;

    static boolean isok(char[] a) {
        int rr = 0, pp = 0, ss = 0;
        for (char ch : a)
            if (ch == 'r') rr++;
            else if (ch == 'p') pp++;
            else ss++;
        return r == rr && p == pp && s == ss;
    }

    static char overcome(char ch) {
        if (ch == 'r') return 's';
        else if (ch == 's') return 'p';
        else return 'r';
    }

    static String getMin(String s) {
        if (s.length() == 1) return s;
        String l = getMin(s.substring(0, s.length() / 2));
        String r = getMin(s.substring(s.length() / 2, s.length()));
        if (l.compareTo(r) < 0) return l + r;
        else return r + l;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("A-small-attempt0.in"));
//        PrintStream cout = new PrintStream("A-small-attempt0.out");
        Scanner cin = new Scanner(new File("A-large.in"));
        PrintStream cout = new PrintStream("A-large.out");
//        Scanner cin = new Scanner(System.in);
//        PrintStream cout = System.out;

        int _case = 0;

        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;
            List<String> buf = new ArrayList<>();
            StringBuilder ans = new StringBuilder();

            n = cin.nextInt();
            r = cin.nextInt();
            p = cin.nextInt();
            s = cin.nextInt();

            char[] a = new char[1 << n];
            char[] b = new char[1 << n];

            boolean ok = false;
            for (char ch : new char[]{'p', 'r', 's'}) {
                a[0] = ch;
                for (int size = 1; 2 * size <= (1 << n); size *= 2) {
                    for (int i = 0; i < size; i++) {
                        b[2 * i] = a[i];
                        b[2 * i + 1] = overcome(a[i]);
                    }
//                    System.out.println(Arrays.toString(b));

                    char[] tmp = a;
                    a = b;
                    b = tmp;
                }

                if (isok(a)) {
                    buf.add(getMin(String.copyValueOf(a).toUpperCase()));
                }
            }

            if (buf.size() == 0) ans.append("IMPOSSIBLE");
            else {
                Collections.sort(buf);
                ans.append(buf.get(0));
            }

            cout.printf("Case #%d: %s%n", _case, ans.toString());
        }

        cin.close();
        cout.close();
    }
}
