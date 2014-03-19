import inserionsort.InsertionSort;
import mergesort.BottomUpMergeSort;
import mergesort.TopDownMergeSort;
import quicksort.QuickSort;
import quicksort.ThreeWayQuickSort;
import selectionsort.SelectionSort;
import shellsort.ShellSort;

import static mergesort.TopDownMergeSort.TopDownMergeSortTest;
import static mergesort.BottomUpMergeSort.BottomUpMergeSortTest;
import static shellsort.ShellSort.ShellSortTest;
import static inserionsort.InsertionSort.InsertionSortTest;
import static selectionsort.SelectionSort.SelectionSortTest;
import static quicksort.QuickSort.QuickSortTest;

public class Main {

    public static void main(String[] args) {
        new SelectionSortTest(new SelectionSort()).test();
        new InsertionSortTest(new InsertionSort()).test();
        new ShellSortTest(new ShellSort()).test();
        new TopDownMergeSortTest(new TopDownMergeSort()).test();
//        new BottomUpMergeSortTest(new BottomUpMergeSort()).test();
        new QuickSortTest(new QuickSort()).test();
        new ThreeWayQuickSort.ThreeWayQuickSortTest(new ThreeWayQuickSort()).test();
    }
}
