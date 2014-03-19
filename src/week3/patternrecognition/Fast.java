package week3.patternrecognition;

import java.util.Comparator;

public class Fast {

    public static void main(String[] args) {
//        String fileName = args[0];
//        int pointNumber = StdIn.readInt();
//        Point[] points = new Point[pointNumber];
//        for (int i = 0; i < pointNumber; i++) {
//            int x = StdIn.readInt();
//            int y = StdIn.readInt();
//            points[i] = new Point(x, y);
//        }
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//
//        Point[] pointsCopy = new Point[points.length];
//        for (int i = 0; i < pointNumber; i++)
//            pointsCopy[i] = points[i];
//
//        for (int i = 0; i < pointNumber; i++) {
//            quickSort(points[i].SLOPE_ORDER, pointsCopy); //TODO: implement 3-way quick sort
//            for (int j = 0; j < points.length; j++) {
//                //TODO: calc if there are four sequential elements on the line
//            }
//        }

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
        a.drawTo(b);
        b.drawTo(c);
        c.drawTo(d);
//        StdOut.print(a);
//        StdOut.print(" -> ");
//        StdOut.print(b);
//        StdOut.print(" -> ");
//        StdOut.print(c);
//        StdOut.print(" -> ");
//        StdOut.println(d);
    }

    private static <T extends Comparable<T>> void quickSort(Comparator<T> comp, T[] arr) {
        shuffle(arr);
        quickSort(comp, arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(Comparator<T> comp, T[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int median = sort(comp, arr, lo, hi);
        quickSort(comp, arr, lo, median - 1);
        quickSort(comp, arr, median + 1, hi);
    }

    private static <T extends Comparable<T>> int sort(Comparator<T> comp, T[] arr, int lo, int hi) {
        int i = (lo + 1 > hi) ? lo : lo + 1, j = hi;
        while (i <= j) { // <= (not <) to get the ability to execute the second while loop
            while (comp.compare(arr[i], arr[lo]) < 0 && i < j) i++;
            while (comp.compare(arr[j], arr[lo]) > 0 && j >= i) j--; // >= (not >) for the situations when arr[lo] is minimum or more general, for the situations when arr[i] > arr[lo]
            if (i < j) swap(arr, i++, j--);
            else if (i == j) break; //because we use <= in outer while-loop, and we must avoid infinite loop
        }
        swap(arr, lo, j);
        return j;
    }

    private static <T extends Comparable<T>> void shuffle(T[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int r = StdRandom.uninform(i);
//            swap(arr, i, r);
//        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
