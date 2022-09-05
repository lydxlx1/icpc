package google.codejam;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class BeamingWithJoySmall {

    static int R, C;
    static char[][] a;

    static Map<Set<Integer>, Integer> hMap;
    static Map<Set<Integer>, Integer> vMap;

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner cin = new Scanner(new File("A-small-attempt0.in"));
//        PrintStream cout = new PrintStream("A-small-attempt0.out");
//        Scanner cin = new Scanner(new File("A-large.in"));
//        PrintStream cout = new PrintStream("A-large.out");
        Scanner cin = new Scanner(System.in);
        PrintStream cout = System.out;

        int _case = 0;
        for (int T = cin.nextInt(); T > 0; T--) {
            _case++;

            R = cin.nextInt();
            C = cin.nextInt();
            a = new char[R][];
            for (int i = 0; i < R; i++)
                a[i] = cin.next().toCharArray();

            hMap = new HashMap<>();
            vMap = new HashMap<>();
            boolean[][] visited = new boolean[R][C];
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    if (a[i][j] != '#' && !visited[i][j]) {
                        Set<Integer> set = new HashSet<>();
                        int cnt = 0;
                        for (int jj = j; jj < C; jj++) {
                            if (a[i][jj] == '#')
                                break;
                            if (a[i][jj] == '|' || a[i][jj] == '-')
                                cnt++;
                            set.add(i * C + jj);
                            visited[i][jj] = true;
                        }
                        if (cnt == 1)
                            hMap.put(set, cnt);
                    }

            visited = new boolean[R][C];
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    if (a[i][j] != '#' && !visited[i][j]) {
                        Set<Integer> set = new HashSet<>();
                        int cnt = 0;
                        for (int ii = i; ii < R; ii++) {
                            if (a[ii][j] == '#')
                                break;
                            if (a[ii][j] == '|' || a[ii][j] == '-')
                                cnt++;
                            set.add(ii * C + j);
                            visited[ii][j] = true;
                        }
                        if (cnt == 1)
                            vMap.put(set, cnt);
                    }

            System.out.println(hMap);
            System.out.println(vMap);


            String ans = "";
            cout.printf("Case #%d: %s%n", _case, ans);
        }

        cin.close();
        cout.close();
    }
}
