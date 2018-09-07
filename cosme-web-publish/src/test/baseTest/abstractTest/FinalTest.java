package baseTest.abstractTest;

/**
 * @author Tanlian
 * @create 2018-09-04 22:40
 **/
public class FinalTest extends Test3 {

    @Override
    void test3() {
        System.out.println("test");
    }

    @org.junit.Test
    public void finalTest() {
        FinalTest finalTest = new FinalTest();
        finalTest.test3();
        finalTest.test1();
        finalTest.test2();
        finalTest.testImplement();
    }

    @Override
    void test2() {
        System.out.println("test2");
    }

    @Override
    void test1() {
        System.out.println("test3");
    }

    @Override
    public void testImplement() {
        System.out.println("可以实现接口");
    }

    private static final String MESSAGE = "taobao";

    @org.junit.Test
    public void testString() {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a, b);
        System.out.println(a + "," + b);
    }
    public static void operator(StringBuffer x, StringBuffer y) {
        System.out.println(x.append(y));
        y = x;
    }
}

