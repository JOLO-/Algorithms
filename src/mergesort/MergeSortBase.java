package mergesort;

public abstract class MergeSortBase {

    protected static <T extends Comparable<T>> void merge(T[] arr, T[] aux, int lo, int mid, int hi) {
        for (int i = 0; i <= hi; i++)
            aux[i] = arr[i];
        int i = lo, j = mid + 1, cur = lo;
        while (i <= mid && j <= hi) {
            if (aux[i].compareTo(aux[j]) <= 0) arr[cur++] = aux[i++];
            else arr[cur++] = aux[j++];
        }
        while (i <= mid) arr[cur++] = aux[i++];
        while (j <= hi) arr[cur++] = aux[j++];
    }
}
