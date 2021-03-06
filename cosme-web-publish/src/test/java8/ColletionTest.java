package java8;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Tanlian
 * @create 2018-08-30 23:51
 **/
public class ColletionTest {


    @Test
    public void test() {
//        Arrays.asList("a", "b", "c").forEach(v-> System.out.println(v));

        Arrays.asList("a", "b", "c").forEach(v-> { System.out.println(v);
            System.out.println(v);
        });

//        Java编译器能够自动识别参数的类型，所以可以省略掉类型不写。
        Arrays.asList("a", "b", "c").sort((e1, e2) -> e1.compareTo(e2));

        Arrays.asList("a", "b", "c").sort((e1, e2) ->{
            int result = e1.compareTo(e2);
            return result;
        });
        System.out.println();


    }

}
