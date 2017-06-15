package ru.timus;

import java.io.*;
import java.util.*;

public class _1439 {

    static class Node {
        static Random rand = new Random();

        int begin;
        int end;
        int size;
        int priority;
        Node left, right;

        public Node(int begin, int end) {
            this.begin = begin;
            this.end = end;
            this.priority = rand.nextInt();
            this.recompute();
        }

        private void recompute() {
            size = end - begin + 1;
            if (left != null)
                size += left.size;
            if (right != null)
                size += right.size;
        }

        static Node merge(Node smaller, Node bigger) {
            if (smaller == null)
                return bigger;
            if (bigger == null)
                return smaller;
            if (smaller.priority < bigger.priority) {
                smaller.right = merge(smaller.right, bigger);
                smaller.recompute();
                return smaller;
            } else {
                bigger.left = merge(smaller, bigger.left);
                bigger.recompute();
                return bigger;
            }
        }

        static Node[] split(Node root, int smallerTotal) {
            List<Node> smaller = new ArrayList<>();
            List<Node> bigger = new ArrayList<>();
            while (root != null) {
                int left = root.left == null ? 0 : root.left.size;
                int leftAndSelf = left + root.end - root.begin + 1;
                if (leftAndSelf <= smallerTotal) {
                    smaller.add(root);
                    root = root.right;
                    smallerTotal -= leftAndSelf;
                } else {
                    bigger.add(root);
                    root = root.left;
                }
            }
            if (smallerTotal > 0) {
                if (bigger.isEmpty()) throw new RuntimeException("Bigger part cannot be empty if smallerTotal > 0");
                Node minBigger = bigger.get(bigger.size() - 1);
                smaller.add(new Node(minBigger.begin, minBigger.begin + smallerTotal - 1)); // This node is always meaningful

                minBigger.begin += smallerTotal; // This node might need to be deleted
                if (minBigger.begin > minBigger.end)
                    bigger.remove(bigger.size() - 1);
            }

            smaller.add(null);
            bigger.add(null);

            for (int i = smaller.size() - 2; i >= 0; i--) {
                smaller.get(i).right = smaller.get(i + 1);
                smaller.get(i).recompute();
            }
            for (int i = bigger.size() - 2; i >= 0; i--) {
                bigger.get(i).left = bigger.get(i + 1);
                bigger.get(i).recompute();
            }

            return new Node[]{smaller.get(0), bigger.get(0)};
        }

        static Node remove(Node root, int rank) {
            Node[] nodes = Node.split(root, rank);
            Node c = nodes[1], b = nodes[0];

            nodes = Node.split(b, rank - 1);
            b = nodes[1];
            if (b.size != 1) {
                throw new RuntimeException("");
            }
            Node a = nodes[0];
            return Node.merge(a, c);
        }

        static Node lookAt(Node root, int rank, int[] res) {
            Node[] nodes = Node.split(root, rank);
            Node c = nodes[1], b = nodes[0];

            nodes = Node.split(b, rank - 1);
            b = nodes[1];
            if (b.size != 1) {
                throw new RuntimeException("");
            }
            res[0] = b.begin;
            Node a = nodes[0];
            return Node.merge(a, Node.merge(b, c));
        }

        static void dfs(Node root) {
            if (root == null) return;
            dfs(root.left);
            System.out.println(root.begin + " " + root.end);
            dfs(root.right);
        }
    }

    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Reader.nextInt();
        int m = Reader.nextInt();
        int[] res = new int[1];
        Node root = new Node(1, n);

        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < m; i++) {
            char ch = Reader.next().charAt(0);
            int rank = Reader.nextInt();

            if (ch == 'L') {
                root = Node.lookAt(root, rank, res);
                joiner.add("" + res[0]);
            } else {
                root = Node.remove(root, rank);
            }
//
//            System.out.println(ch + " " + rank);
//            Node.dfs(root);
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
