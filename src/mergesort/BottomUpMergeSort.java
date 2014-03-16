package mergesort;

import core.SortAlgorithm;
import core.SortTestBase;
import core.Utils;
import inserionsort.InsertionSort;

import java.util.Arrays;

public class BottomUpMergeSort extends MergeSortBase implements SortAlgorithm {

    public static final String NAME = "Bottom Up Merge Sort";

    public static <T extends Comparable<T>> void bottomUpMergeSort(T[] arr) {
        System.out.println("before [arr] " + Arrays.toString(arr)); //TODO: delete
        T[] aux = Arrays.copyOf(arr, arr.length);
        int step = 0;
        while (getNextStep(step) < arr.length) {
            step = getNextStep(step);
            for (int i = 0; i < arr.length; i += step) {
                if ((i + step) < arr.length) {
                    merge(arr, aux, i, i + step / 2 - 1, i + step - 1);
                }
            }
        }
        int mid = calcMedian(arr.length, step);
        //TODO: need to srot the right side: 32 + 16 + 1
        //merge the whole array
        if (mid < arr.length - 1)
            merge(arr, aux, 0, mid, arr.length - 1);
    }

    private static int getNextStep(int step) {
        return (step == 0) ? 2 : 2 * step;
    }

    private static int calcMedian(int length, int step) {
        return (step == 0) ? 0 : (length / step) * step - 1;
    }

    // ==================================
    // ===== FOR UNIT TESTS PURPOSE =====
    // ==================================
    @Override
    public <T extends Comparable<T>> void sort(T[] arr) {
        bottomUpMergeSort(arr);
    }

    @Override
    public String getName() {
        return NAME;
    }

    public static class BottomUpMergeSortTest extends SortTestBase {

        public BottomUpMergeSortTest(SortAlgorithm sortAlgorithm) {
            super(sortAlgorithm);
        }
    }
}
