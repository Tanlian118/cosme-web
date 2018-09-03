package sort;

import org.junit.Test;

/**
 * 快速排序
 * @author Tanlian
 * @create 2018-09-02 18:02
 **/
public class QuickSortTest {

    @Test
    public void QuickSort(int[] arr, int l, int r) {

        if (l >= r)
            return;
        int p = partition(arr, l, r);
        QuickSort(arr, l, p - 1);
        QuickSort(arr, p + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        swap(arr, l, (int) (Math.random() % (r - l + 1)) + 1);
        int v = arr[l];
        int j = l;
        for (int i = j + 1; i <= r; i++) {
            if (arr[i] < v) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr,l ,j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
