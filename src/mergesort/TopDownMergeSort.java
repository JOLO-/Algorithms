package mergesort;

import core.SortAlgorithm;
import core.SortTestBase;

import java.util.Arrays;

public class TopDownMergeSort extends MergeSortBase implements SortAlgorithm {

    private static final String NAME = "Top Down Merge Sort";

    public static <T extends Comparable<T>> void topDownMergeSort(T[] arr) {
        T[] aux = Arrays.copyOf(arr, arr.length);
        sort(arr, aux, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] arr, T[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        if (arr[mid].compareTo(arr[mid + 1]) <= 0) return;
        merge(arr, aux, lo, mid, hi);
    }

    // ==================================
    // ===== FOR UNIT TESTS PURPOSE =====
    // ==================================
    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        topDownMergeSort(arr);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static class TopDownMergeSortTest extends SortTestBase {

        public TopDownMergeSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
