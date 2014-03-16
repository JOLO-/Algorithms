package shellsort;

import core.SortAlgorithm;
import core.SortTestBase;
import core.Utils;

public class ShellSort implements SortAlgorithm {

    private static final String NAME = "Shell Sort";

    public static void shellSort(Comparable[] array) {
        int N = array.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) { //iterate through all elements on the right hand from h (inclusively)
                for (int j = i; j >= h; j -= h) {
                    if (array[j].compareTo(array[j - h]) < 0) Utils.swap(array, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    // ==================================
    // ===== FOR UNIT TESTS PURPOSE =====
    // ==================================
    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        shellSort(arr);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static class ShellSortTest extends SortTestBase {

        public ShellSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
