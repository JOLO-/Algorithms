package week3.patternrecognition;

import java.util.Comparator;

public class Fast {

    public static void main(String[] args) {
        String fileName = args[0];
        int pointNumber = StdIn.readInt();
        Point[] points = new Point[pointNumber];
        for (int i = 0; i < pointNumber; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < pointNumber; i++)
            pointsCopy[i] = points[i];
        shuffle(pointsCopy);

        for (int i = 0; i < pointNumber; i++) {
            Comparator<Point> origin = points[i].SLOPE_ORDER;
            sort(pointsCopy, origin);
            detectAndHandleLines(pointsCopy, origin);
        }
    }

    private static void detectAndHandleLines(Point[] arr, Comparator<Point> origin) {
        int counter = 0;
        for (int i = 1; i < arr.length; i++) {
            if (origin.compare(arr[i - 1], arr[i]) == 0) {
                counter++;
            } else {
                if (counter >= 3) {
                    handleAllCollinearVariants(arr, i - 1 - counter, i - 1);
                }
                counter = 0;
            }
        }
        //handle the last pairs
        int i = arr.length;
        if (counter >= 3) handleAllCollinearVariants(arr, i - 1 - counter, i - 1);
    }

    private static Point[][] handleAllCollinearVariants(Point[] arr, int lo, int hi) {
        if (hi - lo < 3) throw new IllegalArgumentException("pass less 4 collinear points");
        for (int i = lo; i <= hi; i++)
            for (int j = i; j <= hi; j++)
                for (int k = j; k <= hi; k++)
                    for (int t = k; t <= hi; t++)
                        handle4CollinearPoints(arr[i], arr[j], arr[k], arr[t]);
    }

    private static void handle4CollinearPoints(Point a, Point b, Point c, Point d) {
        a.draw();
        b.draw();
        c.draw();
        d.draw();
        a.drawTo(b);
        b.drawTo(c);
        c.drawTo(d);
        StdOut.print(a);
        StdOut.print(" -> ");
        StdOut.print(b);
        StdOut.print(" -> ");
        StdOut.print(c);
        StdOut.print(" -> ");
        StdOut.println(d);
    }

    /*
     * 3-WAY QUICK SORT ALGORITHM
     */
    private static <T extends Comparable<T>> int[] sort(T[] arr, Comparator<T> com) {
        qSort(arr, 0, arr.length - 1, com);
    }

    private static <T extends Comparable<T>> void qSort(T[] arr, int lo, int hi, Comparator<T> com) {
        if (lo >= hi) return;
        int[] res = sort(arr, lo, hi, com);
        int hi1 = res[0];
        int lo2 = res[1];
        qSort(arr, lo, hi1 - 1, com);
        qSort(arr, lo2 + 1, hi, com);
    }

    private static <T extends Comparable<T>> int[] sort(T[] arr, int lo, int hi, Comparator<T> com) {
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        while (i <= gt) {
            if (com.compare(arr[lt], arr[i]) > 0) {
                swap(arr, lt++, i++);
            } else if (com.compare(arr[i], arr[gt]) > 0) {
                swap(arr, i, gt--);
            } else if (com.compare(arr[lt], arr[i]) == 0) {
                i++;
            } else {
                gt--;
            }
        }
        return new int[]{lt, gt};
    }

    private static <T extends Comparable<T>> void shuffle(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int r = StdRandom.uninform(i);
            swap(arr, i, r);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
