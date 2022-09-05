package template.data_structure;

import java.util.Random;

/**
 * Augment a Treap
 */
public class OrderStatisticTree {
    private final static Random RANDOM = new Random();

    protected static class Node {
        final static Node NIL = new Node(0, 0, 0);

        int key;
        int priority;
        Node left, right, parent;

        // extra fields
        int cnt;
        int size;

        private Node(int key, int cnt, int size) {
            this.key = key;
            this.priority = RANDOM.nextInt();
            this.left = this.right = this.parent = NIL;

            this.cnt = cnt;
            this.size = size;
        }

        public static Node createSingleNode(int key) {
            return new Node(key, 1, 1);
        }

        public void push() {
            // Not needed here, but it will be required when lazy tags are used.
        }

        public void update() {
            this.size = this.cnt + this.left.size + this.right.size;
        }
    }

    protected final Node NIL = Node.NIL;
    protected Node root = NIL;
    protected Node lastSearchedNode = null;


    private void rotatePrecheck(Node x) {
        if (x == NIL) throw new RuntimeException("Cannot rotate a NIL node.");
        if (x == root) throw new RuntimeException("Cannot rotate up the root.");
    }

    private void leftRotate(Node x) {
        rotatePrecheck(x);
        //            p                x
        //           / \              / \
        //          a  x     ->      p   c
        //            / \           / \
        //           b  c          a   b
        Node p = x.parent, pp = p.parent;
        Node b = x.left;

        if (pp != NIL) {
            if (pp.left == p) pp.left = x;
            else pp.right = x;
        } else {
            root = x;
        }
        x.parent = pp;

        x.left = p;
        p.parent = x;

        p.right = b;
        if (b != NIL) b.parent = p;

        p.update();
        x.update();
    }

    private void rightRotate(Node x) {
        rotatePrecheck(x);
        //            x                p
        //           / \              / \
        //          a  p     <-      x   c
        //            / \           / \
        //           b  c          a   b
        Node p = x.parent, pp = p.parent;
        Node b = x.right;

        if (pp != NIL) {
            if (pp.left == p) pp.left = x;
            else pp.right = x;
        } else {
            root = x;
        }
        x.parent = pp;

        x.right = p;
        p.parent = x;

        p.left = b;
        if (b != NIL) b.parent = p;

        p.update();
        x.update();
    }

    private void climbUpAndFix(Node x) {
        while (x != NIL) {
            x.update();
            x = x.parent;
        }
    }

    public void insert(int key) {
        if (root == NIL) {
            root = Node.createSingleNode(key);
        } else {
            Node x = search(key);
            if (x == NIL) {
                // key was not in the tree
                x = Node.createSingleNode(key);
                if (key < lastSearchedNode.key) lastSearchedNode.left = x;
                else lastSearchedNode.right = x;
                x.parent = lastSearchedNode;
            } else {
                // key was in the tree
                x.cnt++;
                x.update();
            }

            // Fix heap property.
            Node p = x.parent;
            while (p != NIL && x.priority < p.priority) {
                if (p.left == x) rightRotate(x);
                else leftRotate(x);

                // Note that, after rotation x becomes the role of the old p.
                p = x.parent;
            }

            // Fix the augmented fields from x up to the root.
            climbUpAndFix(x);
        }
    }

    private Node search(int key) {
        Node x = root;
        while (x != NIL) {
            x.push();
            int cmp = Integer.compare(key, x.key);
            lastSearchedNode = x;

            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x;
        }
        return NIL;
    }

    private void remove(Node x) {
        if (x == NIL) throw new RuntimeException("Attempt to remove the NIL node.");
        if (x.left != NIL && x.right != NIL) {
            x.push();
            Node y;
            if (RANDOM.nextInt(2) == 0) {
                // find the successor
                y = x.right;
                while (y.left != NIL) {
                    y.push();
                    y = y.left;
                }
            } else {
                // find the predecessor
                y = x.left;
                while (y.right != NIL) {
                    y.push();
                    y = y.right;
                }
            }

            x.key = y.key;
            x.cnt = y.cnt;
            x = y;
        }

        // Now, at least one of x's child must be NIL
        x.push();
        Node child = x.left != NIL ? x.left : x.right;
        Node p = x.parent;

        child.parent = p;
        if (p == NIL) {
            root = child;
        } else {
            if (p.left == x) p.left = child;
            else p.right = child;
        }
        climbUpAndFix(p);

        x.left = x.right = x.parent = null;
    }

    public void remove(int key) {
        Node x = search(key);
        if (x == NIL) throw new RuntimeException(key + " does not exist in the tree.");

        x.cnt--;
        if (x.cnt <= 0) {
            remove(x);
        } else {
            climbUpAndFix(x);
        }
    }


    /**
     * @param k should be in the range of [1, size].
     * @return the k-th element in sorted order.
     */
    public int select(int k) {
        if (root == NIL) throw new RuntimeException("The root of the tree is NIL.");
        if (k < 1 || k > root.size)
            throw new RuntimeException(String.format("k = %d should be in the range of [%d, %d].", k, 1, root.size));
        Node x = root;
        while (k > 0) {
            x.push();

            if (x.left.size >= k) x = x.left;
            else {
                int leftAndSelf = x.left.size + x.cnt;
                if (leftAndSelf >= k) return x.key;
                else {
                    k -= leftAndSelf;
                    x = x.right;
                }
            }
        }
        throw new RuntimeException("Some fields in the tree are inconsistent.");
    }
}
