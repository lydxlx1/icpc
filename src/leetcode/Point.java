package leetcode;

public class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
