package colletion;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-15 1:00
 **/
public class ArrayListTest {

    /**
     * arrayList 基于数组实现，可动态增加容量，查找速度要求高，增删要求不高
     * LinkedList 基于链表实现，当集合中对访问元素数据速度要求不高，对插入和删除元素数据速度要求高使用
     */

    @Test
    public void arrayListTest() {
        List<Integer> a1 = Lists.newArrayList(2, 3, 1, 4);
        List<String> a2 = Lists.newArrayList("abcd", "ab", "c", "d");

//        a1.removeIf(v -> v.equals(2));

//        a1.remove(1);

        List<String> a3 = a2.subList(0, 2);
        System.out.println(a3);
        a3.add("1");

        a1.set(0, 9);
        List<String> a4 = a2.subList(1, 3);
        a1.addAll(Lists.newArrayList(3, 6, 7, 8));
//        a1.sort((o1, o2) -> o2 - o1);
//      sort要实现Comparable接口  o1-o2表示如果从小到大排序，o2-o1从大到小排序，如果是字符串用compareTo比较
        Collections.sort(a1);
        Collections.sort(a1, (o1, o2) -> o1-o2);
        System.out.println();


    }

    @Test
    public void linkedList() {
        LinkedList<String> linkList = Lists.newLinkedList();
        linkList.add("ssss");
        linkList.add("ddd");
        linkList.add("vvvv");
        linkList.add("cccc");
        for (int i = 0; i < linkList.size(); i++) {
            System.out.println(linkList);
        }
    }
}
