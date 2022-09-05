package template.geometry;

import java.util.Arrays;
import java.util.Comparator;

public class KDTree {
  private final long[][] points;
  private final int dim;
  private final int[] split;
  private final int[] tree; // tree[i] is the index of point stored in node i

  public KDTree(final long[][] points) {
    assert (points != null && points.length > 0);
    this.points = points;
    this.dim = points[0].length;
    this.split = new int[points.length];
    this.tree = new int[points.length];

    Integer[][] indicesBoxed = new Integer[dim][points.length];
    for (int i = 0; i < indicesBoxed.length; i++) {
      final int currentDim = i;
      for (int j = 0; j < indicesBoxed[i].length; j++) {
        indicesBoxed[i][j] = j;
      }
      Arrays.sort(indicesBoxed[i], new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return ((Long) (points[o1][currentDim])).compareTo(points[o2][currentDim]);
        }
      });
    }
    int[][] indices = new int[dim][points.length];
    for (int i = 0; i < indices.length; i++)
      for (int j = 0; j < indices[i].length; j++)
        indices[i][j] = indicesBoxed[i][j];

    build(0, this.points.length - 1, indices, new boolean[this.points.length], new int[this.points.length]);
  }

  private void build(int l, int r, int[][] indices, boolean[] auxVisited, int[] aux) {
    if (l > r)
      return;
    int mid = (l + r) / 2;

    int currentDim = 0;
    double maxVariance = -1E300;
    for (int i = 0; i < dim; i++) {
      double mean = 0;
      double variance = 0;
      for (int j = l; j <= r; j++)
        mean += points[indices[i][j]][i];
      mean /= r - l + 1;
      for (int j = l; j <= r; j++)
        variance += (points[indices[i][j]][i] - mean) * (points[indices[i][j]][i] - mean);
      if (variance > maxVariance) {
        maxVariance = variance;
        currentDim = i;
      }
    }
    split[mid] = currentDim;
    tree[mid] = indices[currentDim][mid];

    for (int i = 0; i < dim; i++)
      if (i != currentDim) {
        int targetPtr = l;

        for (int j = l; j < mid; j++)
          auxVisited[indices[currentDim][j]] = true;
        for (int j = l; j <= r; j++)
          if (auxVisited[indices[i][j]])
            aux[targetPtr++] = indices[i][j];
        for (int j = l; j < mid; j++)
          auxVisited[indices[currentDim][j]] = false;

        aux[targetPtr++] = indices[currentDim][mid];

        for (int j = mid + 1; j <= r; j++)
          auxVisited[indices[currentDim][j]] = true;
        for (int j = l; j <= r; j++)
          if (auxVisited[indices[i][j]])
            aux[targetPtr++] = indices[i][j];
        for (int j = mid + 1; j <= r; j++)
          auxVisited[indices[currentDim][j]] = false;

        for (int j = l; j <= r; j++)
          indices[i][j] = aux[j];
      }

    build(l, mid - 1, indices, auxVisited, aux);
    build(mid + 1, r, indices, auxVisited, aux);
  }

  private long dist2(long[] a, long[] b) {
    long dist = 0;
    for (int i = 0; i < dim; i++)
      dist += (a[i] - b[i]) * (a[i] - b[i]);
    return dist;
  }

  private long minDist; // used for branching-and-bound
  private int nn; // used for branching-and-bound

  private void dfs(int l, int r, long[] query) {
    if (l > r)
      return;
    int mid = (l + r) / 2;
    long dist = dist2(points[tree[mid]], query);
    if (dist < minDist) {
      minDist = dist;
      nn = tree[mid];
    }

    int currentDim = split[mid];
    long gap = query[currentDim] - points[tree[mid]][currentDim];
    if (gap < 0) {
      dfs(l, mid - 1, query);
      if (gap * gap < minDist)
        dfs(mid + 1, r, query);
    } else {
      dfs(mid + 1, r, query);
      if (gap * gap < minDist)
        dfs(l, mid - 1, query);
    }
  }

  public int getNN(long[] query) {
    minDist = Long.MAX_VALUE;
    dfs(0, points.length - 1, query);
    return nn;
  }
}