package singleton;

/**
 * 懒汉式(线程安全，效率低)
 * @author Tanlian
 * @create 2018-08-17 11:09
 **/
public class SingletonSafe {

    private static SingletonSafe instance;

    private SingletonSafe(){}

    public static synchronized SingletonSafe getInstance() {
        if (instance == null) {
             instance = new SingletonSafe();
        }
        return instance;
    }
}
