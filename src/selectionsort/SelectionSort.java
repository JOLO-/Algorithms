package selectionsort;

import core.SortAlgorithm;
import core.SortTestBase;
import core.Utils;

import java.util.Arrays;

public class SelectionSort implements SortAlgorithm {

    private static final String NAME = "Selection Sort";

    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int nextMin = Utils.getIndexOfMin(arr, i);
            Utils.swap(arr, i, nextMin);
        }
    }

    // ==================================
    // ===== FOR UNIT TESTS PURPOSE =====
    // ==================================
    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        selectionSort(arr);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static class SelectionSortTest extends SortTestBase {

        public SelectionSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
