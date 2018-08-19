package singleton;

/**
 * 懒汉式(线程不安全，多线程不能正常工作)
 * @author Tanlian
 * @create 2018-08-17 11:09
 **/
public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance() {
        if (instance == null) {
             instance = new Singleton();
        }
        return instance;
    }
}
