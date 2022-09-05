package codechef;

import java.io.*;
import java.util.*;

/**
 * A good example of persistent treap
 */
public class Genetics {

    static class Node {
        static Random rand = new Random();

        int priority;

        char gene;
        int A = 0, T = 0, C = 0, G = 0;

        Node left, right;

        public Node(char gene, int priority, Node left, Node right) {
            this.gene = gene;
            this.priority = priority;
            this.left = left;
            this.right = right;
            this.recompute();

            if (left != null && priority > left.priority || right != null && priority > right.priority) {
                throw new RuntimeException("Illegal construction");
            }
        }

        public static Node merge(Node smaller, Node bigger) {
            if (smaller == null) return bigger;
            if (bigger == null) return smaller;
            if (smaller.priority < bigger.priority)
                return new Node(smaller.gene, smaller.priority, smaller.left, merge(smaller.right, bigger));
            else
                return new Node(bigger.gene, bigger.priority, merge(smaller, bigger.left), bigger.right);
        }

        public static Node[] split(Node root, int key) {
            List<Node> smaller = new ArrayList<>();
            List<Node> bigger = new ArrayList<>();
            while (root != null) {
                int cnt = 1;
                if (root.left != null)
                    cnt += root.left.A + root.left.T + root.left.C + root.left.G;
                if (cnt < key) {
                    smaller.add(root);
                    root = root.right;
                    key -= cnt; // don't forget this...
                } else {
                    bigger.add(root);
                    root = root.left;
                }
            }
            smaller.add(null);
            bigger.add(null);

            for (int i = smaller.size() - 2; i >= 0; i--) {
                Node old = smaller.get(i);
                smaller.set(i, new Node(old.gene, old.priority, old.left, smaller.get(i + 1)));
            }
            for (int i = bigger.size() - 2; i >= 0; i--) {
                Node old = bigger.get(i);
                bigger.set(i, new Node(old.gene, old.priority, bigger.get(i + 1), old.right));
            }
            return new Node[]{smaller.get(0), bigger.get(0)};
        }

        public static Node append(Node root, char gene) {
            return Node.merge(root, new Node(gene, rand.nextInt(), null, null));
        }

        public static Node mutate(Node root, int pos, char gene) {
            Node[] nodes = Node.split(root, pos + 1);
            Node c = nodes[1];
            Node b = nodes[0];

            nodes = Node.split(b, pos);
            b = nodes[1];
            Node a = nodes[0];

            if (b.left != null || b.right != null) {
                throw new RuntimeException("");
            }

            return Node.merge(a, Node.merge(new Node(gene, rand.nextInt(), null, null), c));
        }

        /**
         * No side-effect
         */
        public static int[] count(Node root, int from, int to) {
            Node[] nodes = Node.split(root, to + 1);
            Node c = nodes[1];
            Node b = nodes[0];

            nodes = Node.split(b, from);
            b = nodes[1];
            Node a = nodes[0];

            int[] res = new int[4];
            res[0] = b.A;
            res[1] = b.G;
            res[2] = b.T;
            res[3] = b.C;

            return res;
        }

        public static Node[] cross(Node root1, Node root2, int k1, int k2) {
            Node[] nodes = Node.split(root1, k1 + 1);
            Node a = nodes[0], b = nodes[1];

            nodes = Node.split(root2, k2 + 1);
            Node c = nodes[0], d = nodes[1];

            return new Node[]{Node.merge(a, d), Node.merge(c, b)};
        }

        private void recompute() {
            A = T = C = G = 0;
            if (left != null) {
                A += left.A;
                T += left.T;
                C += left.C;
                G += left.G;
            }
            if (right != null) {
                A += right.A;
                T += right.T;
                C += right.C;
                G += right.G;
            }
            if (gene == 'A') A++;
            else if (gene == 'T') T++;
            else if (gene == 'C') C++;
            else G++;
        }
    }


    static void dfs(Node root) {
        if (root == null)
            return;
        dfs(root.left);
        System.out.print(root.gene);
        dfs(root.right);
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<Integer, Node> map = new HashMap<>();
        int n = Reader.nextInt();
        for (int i = 1; i <= n; i++) {
            String s = Reader.next();
            Node root = null;
            for (char ch : s.toCharArray())
                root = Node.append(root, ch);
            map.put(i, root);
        }

        int m = Reader.nextInt();
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < m; i++) {
            String op = Reader.next();
            if (op.equals("MUTATE")) {
                int id = Reader.nextInt();
                int k = Reader.nextInt();
                char gene = Reader.next().charAt(0);
                map.put(id, Node.mutate(map.get(id), k, gene));
            } else if (op.equals("COUNT")) {
                int id = Reader.nextInt();
                int from = Reader.nextInt();
                int to = Reader.nextInt();
                int[] res = Node.count(map.get(id), from, to);
                joiner.add(res[0] + " " + res[1] + " " + res[2] + " " + res[3]);
            } else {
                int id1 = Reader.nextInt();
                int id2 = Reader.nextInt();
                int k1 = Reader.nextInt();
                int k2 = Reader.nextInt();
                Node[] nodes = Node.cross(map.get(id1), map.get(id2), k1, k2);
                map.put(++n, nodes[0]);
                map.put(++n, nodes[1]);
            }

//            for (int j = 1; j <= n; j++) {
//                System.out.print(j + ": ");
//                dfs(map.get(j));
//                System.out.println();
//            }
//            System.out.println();
        }
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
