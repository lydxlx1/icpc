package leetcode;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;


/**
 * Treap solution with O(log n) time per each operation.
 */
public class _2296_Design_A_Text_Editor {

    class TextEditor {
        static class Treap {
            private static int BUFFER_SIZE = 1234;
            private static Random rand = new Random();
            private static Treap[] smaller = new Treap[BUFFER_SIZE];
            private static Treap[] bigger = new Treap[BUFFER_SIZE];

            private char key;
            private int priority;
            private int size;
            private Treap left, right;

            public Treap(char key) {
                this.key = key;
                this.priority = rand.nextInt();
                this.size = 1;
            }

            private void recompute() {
                this.size = 1 + (left != null ? left.size : 0) + (right != null ? right.size : 0);
            }

            static Treap merge(Treap smaller, Treap bigger) {
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

            public void inorder() {
                if (left != null) {
                    left.inorder();
                }
                System.err.println(String.format("key=%d, size=%d", key, size));
                if (right != null) {
                    right.inorder();
                }
            }

            static Treap[] split(Treap root, int leftSize) {
                int smallerSize = 0, biggerSize = 0;
                while (root != null)
                    if ((root.left != null ? root.left.size + 1 : 1) <= leftSize) {
                        smaller[smallerSize++] = root;
                        leftSize -= root.left != null ? root.left.size + 1 : 1;
                        root = root.right;
                    } else {
                        bigger[biggerSize++] = root;
                        root = root.left;
                    }
                smaller[smallerSize++] = null;
                bigger[biggerSize++] = null;
                for (int i = 0; i < smallerSize - 1; i++)
                    smaller[i].right = smaller[i + 1];
                for (int i = 0; i < biggerSize - 1; i++)
                    bigger[i].left = bigger[i + 1];
                for (int i = smallerSize - 2; i >= 0; i--)
                    smaller[i].recompute();
                for (int i = biggerSize - 2; i >= 0; i--)
                    bigger[i].recompute();
                return new Treap[]{smaller[0], bigger[0]};
            }
        }


        int cursorSize;
        Treap root = null;

        public TextEditor() {
            cursorSize = 0;
            root = null;
        }

        public void addText(String text) {
            Treap[] tmp = Treap.split(root, cursorSize);
            root = tmp[0];
            for (char ch : text.toCharArray()) {
                root = Treap.merge(root, new Treap(ch));
            }
            root = Treap.merge(root, tmp[1]);
            cursorSize += text.length();
        }

        public int deleteText(int k) {
            int actualDeleteCnt = Math.min(cursorSize, k);
            int cursorSizeAfter = cursorSize - actualDeleteCnt;

            Treap[] tmp = Treap.split(root, cursorSizeAfter);
            root = tmp[0];
            tmp = Treap.split(tmp[1], actualDeleteCnt);
            root = Treap.merge(root, tmp[1]); // Dropping tmp[0]

            cursorSize -= actualDeleteCnt;
            return actualDeleteCnt;
        }

        private void inorder(Treap root, StringBuffer sb) {
            if (root == null) return;
            inorder(root.left, sb);
            sb.append(root.key);
            inorder(root.right, sb);
        }

        private String getString() {
            Treap[] tmp = Treap.split(root, cursorSize);
            Treap c = tmp[1];
            tmp = Treap.split(tmp[0], Math.max(0, cursorSize - 10));

            StringBuffer sb = new StringBuffer();
            inorder(tmp[1], sb);
            root = Treap.merge(tmp[0], tmp[1]);
            root = Treap.merge(root, c);
            return sb.toString();
        }

        public String cursorLeft(int k) {
            cursorSize = Math.max(0, cursorSize - k);
            return getString();
        }

        public String cursorRight(int k) {
            cursorSize = root == null ? cursorSize : Math.min(root.size, cursorSize + k);
            return getString();
        }
    }


    _2296_Design_A_Text_Editor() {
        TextEditor editor = new TextEditor();
        editor.addText("leetcode");
//        System.out.println(editor.cursorSize);
//        System.out.println(editor.getString());
        editor.deleteText(4);
        System.out.println(editor.getString());
        editor.addText("practice");
        System.out.println(editor.getString());
        System.out.println(editor.cursorRight(3));
        System.out.println(editor.cursorLeft(8));
        editor.deleteText(10);
        System.out.println(editor.cursorLeft(2));
        System.out.println(editor.cursorRight(6));


    }
    public static void main(String[] args) throws Exception {
        Reader.init(System.in);
        BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

        new _2296_Design_A_Text_Editor();

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
