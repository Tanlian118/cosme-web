package colletion;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-15 12:16
 **/
public class MapTest {

    /**
     * m1是基于哈希表的Map实现的的，一个Key对应一个Value，允许使用null键和null值
     */

    @Test
    public void hashMapTest() {
        Map<Integer, String> m1 = Maps.newHashMap();
        m1.put(1, "a");
        m1.put(2, "b");
        m1.put(3, "c");
        m1.put(4, "d");
        m1.put(5, "e");
        m1.put(6, "f");

//        Collection<String> m2 = m1.values();
//        System.out.println(m2);
//        String s = m1.get(1);
//        System.out.println(s);
//        遍历m1，将key value存储到set集合
//        Set<Map.Entry<Integer, String>> m3 = m1.entrySet();
//        System.out.println(m3);

//        m1.containsKey(0);
        boolean c = m1.containsValue("c");
        System.out.println(c);

        m1.entrySet().forEach(v -> {
            String value = v.getValue();
            Integer key = v.getKey();
            System.out.println(key + ":" + value);
        });

        for (Map.Entry<Integer, String> entry : m1.entrySet()) {

        }

        Set<Integer> keySet = m1.keySet();
        for (Integer key : keySet) {
            String s = m1.get(key);

        }

        Collection<String> values = m1.values();
        for (String value : values) {
        }

        String a = m1.getOrDefault(1, "jnkj");
        System.out.println(a);
    }
}