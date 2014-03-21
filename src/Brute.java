public class Brute {

    public static void main(String[] args) {
        Point[] points = prepare(args[0]);
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                for (int k = j + 1; k < points.length; k++)
                    for (int l = k + 1; l < points.length; l++)
                        if (isCollinear(points[i], points[j], points[k], points[l]))
                            handleCollinear(points[i], points[j], points[k], points[l]);
    }

    private static Point[] prepare(String fileName) {
        In in = new In(fileName);
        int pointNumber = in.readInt();
        Point[] points = new Point[pointNumber];
        int ind = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            points[ind++] = new Point(x, y);
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        return points;
    }

    private static boolean isCollinear(Point a, Point b, Point c, Point d) {
        double first = a.slopeTo(b);
        double second = b.slopeTo(c);
        double third = c.slopeTo(d);
        return first == second && second == third;
    }

    private static void handleCollinear(Point a, Point b, Point c, Point d) {
        a.draw();
        b.draw();
        c.draw();
        d.draw();
        Point[] sorted = new Point[] {a, b, c, d};
        insertionSort(sorted);
        sorted[0].drawTo(sorted[3]);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sorted.length; i++) {
            builder.append(sorted[i].toString());
            if (i < sorted.length - 1)
                builder.append(" -> ");
        }
        StdOut.println(builder.toString());
    }

    /*
     * Insertion Sort
     */

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        insertionSort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void insertionSort(T[] arr, int lo, int hi) {
        for (int i = lo; i < hi; i++)
            for (int j = i + 1, k = i; k >= 0 && arr[k].compareTo(arr[j]) > 0; j--, k--)
                swap(arr, k, j);
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
