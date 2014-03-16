package inserionsort;

import core.SortAlgorithm;
import core.SortTestBase;
import core.Utils;

public class InsertionSort implements SortAlgorithm {

    private static final String NAME = "Insertion Sort";

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        insertionSort(arr, 0, arr.length - 1);
    }

    public static <T extends Comparable<T>> void insertionSort(T[] arr, int lo, int hi) {
        for (int i = lo; i < hi; i++)
            for (int j = i + 1, k = i; k >= 0 && arr[k].compareTo(arr[j]) > 0; j--, k--)
                Utils.swap(arr, k, j);
    }

    // ==================================
    // ===== FOR UNIT TESTS PURPOSE =====
    // ==================================
    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        insertionSort(arr);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static class InsertionSortTest extends SortTestBase {
        public InsertionSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
