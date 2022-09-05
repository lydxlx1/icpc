package template.tree;

import java.util.Random;

public class Treap {
    private static int BUFFER_SIZE = 1234;
    private static Random rand = new Random();
    private static Treap[] smaller = new Treap[BUFFER_SIZE];
    private static Treap[] bigger = new Treap[BUFFER_SIZE];

    private int key;
    private int priority;
    private int size;
    private Treap left, right;

    public Treap(int key) {
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

    static Treap[] split(Treap root, int key) {
        int smallerSize = 0, biggerSize = 0;
        while (root != null)
            if (root.key < key) {
                smaller[smallerSize++] = root;
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

    static Treap insert(Treap root, int key) {
        Treap[] subtrees = split(root, key);
        Treap a = subtrees[0];
        Treap b = subtrees[1];
        return merge(merge(a, new Treap(key)), b);
    }
}
