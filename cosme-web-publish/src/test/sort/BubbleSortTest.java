package sort;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-09-02 17:21
 **/
public class BubbleSortTest {

    @Test
    public void test() {
        int[] arr = {2, 3, 54, 23, 65, 21, 8};

        for (int i = arr.length -1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j ,j+1);
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }

    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
