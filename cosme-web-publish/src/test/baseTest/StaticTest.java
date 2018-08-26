package baseTest;

/**
 * @author Tanlian
 * @create 2018-08-26 14:35
 **/
public class StaticTest {
    private static int a = B();

    static {
        System.out.println("Enter StaticTest.baseTest block");
    }


//    静态资源的加载顺序是严格按照静态资源的定义顺序来加载的。
    public static void main(String[] args) {
        new StaticTest();
    }

    public static int B() {
        System.out.println("Enter StaticTest.B()");
        return 1;
    }

//    静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问。
    static {
        c = 3;
//        System.out.println(c);
    }

    private static int c;
}
