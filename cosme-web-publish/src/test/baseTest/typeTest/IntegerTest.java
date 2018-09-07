package baseTest.typeTest;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-08-26 16:58
 **/
public class IntegerTest {


    @Test
    public void test() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2); //true
        System.out.println(i3 == i4); //false
    }
//Byte、Short、Integer、Long、Char以128作为分界线做了缓存

    @Test
    public void test2() {
        Double d1 = 100.0;
        Double d2 = 100.0;
        Double d3 = 200.0;
        Double d4 = 200.0;
        System.out.println(d1 == d2); //false
        System.out.println(d3 == d4); //false
    }
}
