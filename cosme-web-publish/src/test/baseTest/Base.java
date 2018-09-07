package baseTest;

/**
 * @author Tanlian
 * @create 2018-09-05 23:29
 **/
public class Base {

    public void method() {
        System.out.println("Base");
    }
}

class Son extends Base {
    public void method() {
        System.out.println("Son");
    }

    public void methodB() {
        System.out.println("SonB");
    }

    public static void main(String[] args) {

        Base base = new Son();
        base.method();  //son

//      父类的引用无法访问子类独有的方法,只能调用共有的方法
//       base.methodB();

        Base base1 = new Base();
        base1.method();

//        编译看左边，运行看右边。意思编译时候，看左边有没有该方法，
//        运行的时候结果看 new 的对象是谁，就调用的谁。
        Son son = (Son)base;
        son.method();
        son.methodB();
    }
}
