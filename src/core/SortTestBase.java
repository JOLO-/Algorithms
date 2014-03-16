package core;

import java.util.Arrays;

public class SortTestBase {

    private static final int[] ARRAY_LENGTHS = {1, 5, 6, 17, 33, 49, 80, 100, 128, 228, 300};
    private static final int EXECUTE_N_TIMES = ARRAY_LENGTHS.length;
    private final SortAlgorithm sortAlgorithm;

    public SortTestBase(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    private static class SortException extends RuntimeException {
        private SortException(String s) {
            super(s);
        }
    }

    public void test() {
        for (int i = 0; i < EXECUTE_N_TIMES; i++)
            testSort(ARRAY_LENGTHS[i]);
        System.out.println(sortAlgorithm.getName() + " works correct");
    }

    private void testSort(int length) {
        Integer[] arr = Utils.genRandomArray(length);
        sortAlgorithm.sort(arr);
        if (!Utils.isSorted(arr))
            throw new SortException(sortAlgorithm.getName() + " " + Arrays.toString(arr));
    }
}
