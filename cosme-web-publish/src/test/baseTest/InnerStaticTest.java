package baseTest;

/**
 * @author Tanlian
 * @create 2018-08-26 14:58
 **/
public class InnerStaticTest extends InnerStatic {

//   static一般情况下来说是不可以修饰类的，如果static要修饰一个类，
// 说明这个类是一个静态内部类(static只能修饰一个内部类)，也就是匿名内部类。
    static {
        System.out.println("B.static block");
    }

    public InnerStaticTest() {
        System.out.println("B.constructor()");
    }

//   静态代码块是严格按照父类静态代码块->子类静态代码块的顺序加载的，且只加载一次
    public static void main(String[] args) {
        new InnerStaticTest();
        new InnerStaticTest();
    }
}
