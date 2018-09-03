package sort;

import org.junit.Test;

/**
 * 选择排序 时间复杂度：O(2^N)
 * @author Tanlian
 * @create 2018-09-02 16:40
 **/
public class SelectSortTest {

    @Test
    public void selectSortTest( ) {
        int[] arr = {2,36,12,9,13,24,78,65};

//        外层循环：2,36,12,9,13,24,78,65    minIndex = 0 ->2
//        内层循环：36,12,9,13,24,78,65    用2与内层循环做比较
//        第一次排序：2,36,12,9,13,24,78,65

//                  外:36,12,9,13,24,78,65    minIndex = 0 ->36
//                  内：12,9,13,24,78,65      9 < 36,交换位置
//        第二次排序：2,9,36,12,13,24,78,65


        for (int i = 0; i < arr.length ; i++) {

//          寻找[i,n)区间里最小值的索引
            int minIndex = i;
//          遍历除minindex之后的元素，将元素进行排序
            for (int j = i + 1; j < arr.length ; j++) {

                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
        for (int i1 : arr) {
            System.out.println(i1);
        }

    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i]= arr[j];
        arr[j] = t;
    }

    @Test
    public void Test2() {
        int[] arr = {2,45,6,8,9,34,21};

        for (int i = 0; i <arr.length ; i++) {
            int minIndex = i;
            for (int j = i + 1 ; j < arr.length ; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
