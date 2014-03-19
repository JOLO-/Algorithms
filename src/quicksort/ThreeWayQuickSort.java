package quicksort;

import core.SortAlgorithm;
import core.SortTestBase;
import core.Utils;

public class ThreeWayQuickSort implements SortAlgorithm {

    public static final String NAME ="Three Way Quick Sort";

    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        Utils.shuffle(arr);

    }

    @Override
    public String getName() { return NAME; }

    private  <T extends Comparable<T>> void qSort(T[] arr, int lo, int hi) {
        int[] res = sort(arr, lo, hi);
        int hi1 = res[0];
        int lo2 = res[1];
        qSort(arr, lo, hi1 - 1);
        qSort(arr, lo2 + 1, hi);
    }

    private  <T extends Comparable<T>> int[] sort(T[] arr, int lo, int hi) {
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        while (i < gt) {
            if (arr[lt].compareTo(arr[i]) > 0) {
                Utils.swap(arr, lt++, i++);
            } else if (arr[lt].compareTo(arr[i]) == 0) {
                i++;
            } else if (arr[i].compareTo(arr[gt]) > 0) {
                Utils.swap(arr, i, gt--);
            } else if (arr[i].compareTo(arr[gt]) < 0) {
                gt--;
            }
        }
        return new int[] {lt, i};
    }

    public static class ThreeWayQuickSortTest extends SortTestBase {

        public ThreeWayQuickSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
