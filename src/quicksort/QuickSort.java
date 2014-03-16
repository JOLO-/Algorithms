package quicksort;

import core.SortTestBase;
import core.Utils;
import core.SortAlgorithm;

public class QuickSort implements SortAlgorithm {

    private static final String NAME = "Quick Sort";

    public static <T extends Comparable<T>> void quickSort(T[] arr) {
        Utils.shuffle(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int median = sort(arr, lo, hi);
        quickSort(arr, lo, median - 1);
        quickSort(arr, median + 1, hi);
    }

    private static <T extends Comparable<T>> int sort(T[] arr, int lo, int hi) {
        int i = (lo + 1 > hi) ? lo : lo + 1, j = hi;
        while (i <= j) { // <= (not <) to get the ability to execute the second while loop
            while (arr[i].compareTo(arr[lo]) < 0 && i < j) i++;
            while (arr[j].compareTo(arr[lo]) > 0 && j >= i) j--; // >= (not >) for the situations when arr[lo] is minimum or more general, for the situations when arr[i] > arr[lo]
            if (i < j) Utils.swap(arr, i++, j--);
            else if (i == j) break; //because we use <= in outer while-loop, and we must avoid infinite loop
        }
        Utils.swap(arr, lo, j);
        return j;
    }

    // ==================================
    // ===== FOR UNIT TESTS PURPOSE =====
    // ==================================
    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        quickSort(arr);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static class QuickSortTest extends SortTestBase {
        public QuickSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
