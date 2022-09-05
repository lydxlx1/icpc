package template.data_structure;

public class SegmentTree {

    /*
     * report the max element of an interval
     * can added a constant to an interval
     */
    static class SegmentTree1 {
        private int[] input;
        private int[] begin;
        private int[] end;
        private int[] max;
        private int[] mask;
        private int left, right, delta;

        public SegmentTree1(int[] input) {
            this.input = input;
            begin = new int[4 * input.length];
            end = new int[4 * input.length];
            max = new int[4 * input.length];
            mask = new int[4 * input.length];
            build(1, 0, input.length - 1);
        }

        private void build(int i, int left, int right) {
            begin[i] = left;
            end[i] = right;
            if (left == right) max[i] = input[left];
            else {
                int mid = left + (right - left) / 2;
                build(2 * i, left, mid);
                build(2 * i + 1, mid + 1, right);
                max[i] = Math.max(max[2 * i], max[2 * i + 1]);
            }
        }

        private void push(int i) {
            max[i] += mask[i];
            if (begin[i] < end[i]) {
                mask[2 * i] += mask[i];
                mask[2 * i + 1] += mask[i];
            }
            mask[i] = 0;
        }

        public void update(int left, int right, int delta) {
            this.left = left;
            this.right = right;
            this.delta = delta;
            update(1);
        }

        private void update(int i) {
            push(i);
            if (right < begin[i] || end[i] < left) return;
            else if (left <= begin[i] && end[i] <= right) {
                mask[i] += delta;
                push(i);
            } else {
                update(2 * i);
                update(2 * i + 1);
                max[i] = Math.max(max[2 * i], max[2 * i + 1]);
            }
        }

        public int query(int left, int right) {
            this.left = left;
            this.right = right;
            return query(1);
        }

        private int query(int i) {
            push(i);
            if (right < begin[i] || end[i] < left) return Integer.MIN_VALUE;
            else if (left <= begin[i] && end[i] <= right) return max[i];
            else return Math.max(query(2 * i), query(2 * i + 1));
        }
    }
}
