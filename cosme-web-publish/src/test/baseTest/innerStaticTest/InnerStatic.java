package baseTest.innerStaticTest;

/**
 * @author Tanlian
 * @create 2018-08-26 14:58
 **/
public class InnerStatic {

//    内部静态代码块，可以不依赖于外部类实例被实例化。
    static {
        System.out.println("A.static block");
    }

    public InnerStatic() {
        System.out.println("A.constructor()");
    }
}
