package colletion;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Tanlian
 * @create 2018-08-15 11:50
 **/
public class SetCollectionTest {

    /**
     * set集合的两个实现类：hashSet treeSet
     *
     * HashSet集合中允许有null元素，TreeSet集合中不允许有null元素。
     */

//    HashSet是一个非同步的方法，如果要在多个线程中使用，要注意进行同步封装
//    HashSet是一个无序的集合，基于HashMap实现；
    @Test
    public void hashSetTest() {
        Set<Integer> s1 = Sets.newHashSet(1,2,3,4);
        Set<Integer> s2 = Sets.newHashSet(3,4,6,7);

//        s1.addAll(s2);
//        s1.clear();

        boolean b = s1.contains(12);
        System.out.println(b);
        boolean b1 = s1.containsAll(s2);
        System.out.println(b1);

//        s1.remove(3);
//        s1.removeAll(Sets.newHashSet(2,1));

//        取s1和s2的交集，并将交集结果返回s1
//        s1.retainAll(s2);


        s1.removeIf(v->!v.equals(1));
        System.out.println();
    }


//    treeSet通过compareTo或者compare方法中的来保证元素的唯一性。
//    添加元素时必须要实现Comparable接口。重写ompareTo()方法，当compareTo()函数返回值为0时，说明两个对象相等，此时该对象不会添加进来。
    @Test
    public void treeSet() {
        TreeSet<Comparable> treeSet = Sets.newTreeSet();
        treeSet.add("asdf");
        treeSet.add("gffv");
        treeSet.add("errt");
        treeSet.add("swcs");
        treeSet.add("ikkn");
        treeSet.add("qwew");

        for (Comparable comparable : treeSet) {
            System.out.println(comparable);
        }
    }


}
