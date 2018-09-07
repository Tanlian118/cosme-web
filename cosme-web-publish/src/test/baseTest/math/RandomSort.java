package baseTest.math;

import java.util.*;

/**
 * 用程序给出随便大小的10个数，序号为1-10，按从小到大顺序输出，并输出相应的序号
 * @author Tanlian
 * @create 2018-09-07 21:58
 **/
public class RandomSort {

    public static void printRandomBySort() {
        Random random = new Random(); // 创建随机数生成器
        List list = new ArrayList();
        // 生成10个随机数，并放在集合list中
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(1000));
        }
        Collections.sort(list); // 对集合中的元素进行排序
        Iterator it = list.iterator();
        int count = 0;
        while (it.hasNext()) { // 顺序输出排序后集合中的元素
            System.out.println(++count + ": " + it.next());

        }
    }
    public static void main(String[] args) {
        printRandomBySort();
    }
}

