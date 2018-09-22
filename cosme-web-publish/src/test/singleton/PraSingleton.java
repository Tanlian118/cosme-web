package singleton;


/**
 * 懒汉式
 * @author Tanlian
 * @create 2018-09-07 22:18
 **/
public class PraSingleton {

   private static PraSingleton instance = null;

    public static synchronized PraSingleton  getInstance() {
        if (instance == null) {
            return new PraSingleton();
        }
        return instance;
    }
}
