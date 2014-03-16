package core;

import java.util.Random;

public class Utils {

    private static final Random random = new Random();

    public static <T extends Comparable<T>> void shuffle(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int r = random.nextInt(i);
            swap(arr, i, r);
        }
    }

    public static <T extends Comparable<T>> void swap(T[] arr, int i, int j) {
        if (i == j) return;
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) return false;
        }
        return true;
    }

    public static Integer[] genRandomArray(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static <T extends Comparable<T>> int getIndexOfMin(T[] arr, int startIndex) {
        if (startIndex >= arr.length)
            throw new ArrayIndexOutOfBoundsException("StartIndex is greater or equals to array length");
        T min = arr[startIndex];
        int minIndex = startIndex;
        for (int i = startIndex; i < arr.length; i++) {
            if (arr[i].compareTo(min) < 0) {
                minIndex = i;
                min = arr[i];
            }
        }
        return minIndex;
    }

    /**
     * Checks if arguments is the one of the powers of two
     */
    public static boolean isPowerOfTwo(int val) {
        boolean isPowerOfTwo = true;
        int i = val;
        while (i > 0) {
            if (i % 2 != 0) {
                isPowerOfTwo = false;
                break;
            } else i = i / 2;
        }
        return isPowerOfTwo;
    }
}
