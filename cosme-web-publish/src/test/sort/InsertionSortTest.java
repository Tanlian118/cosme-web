package sort;

import org.junit.Test;

/**
 * 插入排序
 *
 * @author Tanlian
 * @create 2018-09-02 17:22
 **/
public class InsertionSortTest {

    @Test
    public void test() {

        int[] arr = {2, 3, 54, 23, 65, 21, 8};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j >= 0; j--) {
                if (arr[j] < arr[j - 1])
                    swap(arr, j, j - 1);
                else break;
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

//    改良版插入排序
    @Test
    public void test2() {
        int[] arr = {2, 3, 54, 23, 65, 21, 8};

        for (int i = 0; i < arr.length; i++) {
            int e = arr[i];
            int j = i;
            for (; j > 0 ; j--) {
                if (e < arr[j - 1])
                    arr[j] = arr[j-1];
                else break;
            }
            arr[j] =  e;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
