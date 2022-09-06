package template.data_structure;

/**
 * Integer version of Binary Indexed Tree (BIT)
 */
public class BinaryIndexedTreeInt {

    int bit[];

    /**
     * @param capacity - Note that index starts from 1.
     */
    public BinaryIndexedTreeInt(int capacity) {
        this.bit = new int[capacity + 10];
    }

    static int lowbit(int x) {
        return x & -x;
    }

    /**
     * @param x
     * @param val Performs the operation: bit[x] += val
     */
    void add(int x, int val) {
        for (; x < bit.length; x += lowbit(x)) {
            bit[x] += val;
        }
    }

    /**
     * @param x
     * @return bit[1] + bit[2] + ... + bit[x]
     */
    int sum(int x) {
        int sum = 0;
        for (; x > 0; x -= lowbit(x)) {
            sum += bit[x];
        }
        return sum;
    }
}