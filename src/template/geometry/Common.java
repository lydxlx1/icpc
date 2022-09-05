package template.geometry;

import java.util.Arrays;
import java.util.Comparator;

public class Common {
    static class Point {
        static final Point ORIGIN = new Point(0, 0);
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Vector {
        int x, y;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Vector(Point a, Point b) {
            this.x = b.x - a.x;
            this.y = b.y - a.y;
        }

        public long crossProduct(Vector other) {
            return (long) x * other.y - (long) y * other.x;
        }
    }

    /**
     * Sort points[0..size) with respect to origin
     */
    static void polarAngleSort(Point[] points, int size, final Point origin) {
        final class Helper {
            int getRegion(Point p) {
                if (p.x == origin.x && p.y == origin.y)
                    return 0;
                if (p.y > origin.y || (p.y == origin.y && p.x > origin.x))
                    return 1;
                else
                    return 2;
            }
        }

        final Helper helper = new Helper();
        Arrays.sort(points, 0, size, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int region1 = helper.getRegion(p1);
                int region2 = helper.getRegion(p2);
                if (region1 < region2)
                    return -1;
                else if (region1 > region2)
                    return 1;
                else
                    return Long.signum(new Vector(origin, p1).crossProduct(new Vector(origin, p2))) * -1;
            }
        });
    }
}
