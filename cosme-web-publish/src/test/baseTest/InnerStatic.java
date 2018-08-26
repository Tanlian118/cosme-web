package baseTest;

/**
 * @author Tanlian
 * @create 2018-08-26 14:58
 **/
public class InnerStatic {

    static {
        System.out.println("A.static block");
    }

    public InnerStatic() {
        System.out.println("A.constructor()");
    }
}
