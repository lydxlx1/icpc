package template.geometry;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Use a different Point class if the type of coordinates needs to be long or double.
 */
public class ClosestPair {

    public static final long INF = Long.MAX_VALUE;

    public static long sqr(long x) {
        return x * x;
    }

    public static long dist2(Point a, Point b) {
        return sqr((long) a.x - b.x) + sqr((long) a.y - b.y);
    }

    public static long shortestPairwiseDistanceSquare(Point[] points) {
        final Point[] aux = new Point[points.length];
        Arrays.sort(points, Comparator.comparingInt(u -> u.x));
        class MergeSort {
            private long merge(int l, int r) {
                if (l == r) return INF;
                else {
                    int mid = (l + r) / 2, medianX = points[mid].x;
                    long deltaSquare = Math.min(merge(l, mid), merge(mid + 1, r));

                    // merge along y-coordinates
                    int i = l, j = mid + 1, k = l;
                    while (i <= mid && j <= r)
                        if (points[i].y < points[j].y) aux[k++] = points[i++];
                        else aux[k++] = points[j++];
                    while (i <= mid) aux[k++] = points[i++];
                    while (j <= r) aux[k++] = points[j++];


                    // copy points back and collect those points within the central strip simultaneously
                    for (i = l, k = l; i <= r; i++) {
                        points[i] = aux[i];
                        if (sqr(points[i].x - medianX) < deltaSquare) aux[k++] = points[i];
                    }

                    // Now, aux[l..k) contains all the points in the central strip sorted in non-decreasing y order.
                    for (i = l; i < k; i++) {
                        // We guarantee the following loop will run at most 8 iterations.
                        for (j = i - 1; j >= l && sqr(aux[i].y - aux[j].y) < deltaSquare; j--)
                            deltaSquare = Math.min(deltaSquare, dist2(aux[i], aux[j]));
                    }
                    return deltaSquare;
                }
            }
        }
        return new MergeSort().merge(0, points.length - 1);
    }
}