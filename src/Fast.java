import java.util.Comparator;

public class Fast {

    private static Point[] points;
    private static Point[] pointsCopy;
    private static Point[] collinear;

    public static void main(String[] args) {
        prepare(args[0]);

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];
            Comparator<Point> comparator = points[i].SLOPE_ORDER;
            if (!isPresent(origin)){
                sort(pointsCopy, comparator);
                detectAndHandleCollinearSequences(pointsCopy, comparator);
            }
        }
    }

    /*
     * Read input data and prepare
     */
    private static void prepare(String fileName) {
        In in = new In(fileName);
        int pointNumber = in.readInt();
        Point[] array = new Point[pointNumber];
        int ind = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            array[ind++] = new Point(x, y);
        }
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        points = array;
        pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++)
            pointsCopy[i] = points[i];
        shuffle(pointsCopy);
        collinear = new Point[points.length];
    }

    private static void detectAndHandleCollinearSequences(Point[] arr, Comparator<Point> origin) {
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
        //handle the last sequence
        int i = arr.length - 1;
        if (counter >= 3) handleAllCollinearVariants(arr, i - 1 - counter, i - 1);
    }

    private static Point[][] handleAllCollinearVariants(Point[] arr, int lo, int hi) {
        if (hi - lo < 3) throw new IllegalArgumentException("pass less 4 collinear points");
        for (int i = lo; i <= hi; i++)
            for (int j = i; j <= hi; j++)
                for (int k = j; k <= hi; k++)
                    for (int t = k; t <= hi; t++)
                        handleCollinear(arr[i], arr[j], arr[k], arr[t]);
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

        storeResults(a, b, c, d);
    }

    /*
     * Checking if this point has been already participated in line
     */ //TODO: implement custom collection with binary search

    private static boolean isPresent(Point p) {
        for (int i = 0; i < collinear.length; i++)
            if (collinear[i].compareTo(p) == 0) return true;
        else return false;
    }

    private static void storeResults(Point a, Point b, Point c, Point d) {
        putIfAbsent(a);
        putIfAbsent(b);
        putIfAbsent(c);
        putIfAbsent(d);
    }

    private static boolean putIfAbsent(Point p) {
        int index = suggestPosition(p);
        if (index != -1) {
            collinear[index] = p;
            return true;
        }
        return false;
    }

    /**
     * Returns -1 if point is already present in the {@code collinear} array, otherwise returns index where we should insert new point.
     * @param p Point to insert
     * @return index where to insert
     */
    private static int suggestPosition(Point p) {
        for (int i = 0; i < collinear.length; i++) {
            if (collinear[i] == null) return i;
            else if (collinear[i].compareTo(p) == 0) return -1;
            else continue;
        }

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
            int r = StdRandom.uniform(i);
            swap(arr, i, r);
        }
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

    /*
     * Basic Sort
     */
    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
