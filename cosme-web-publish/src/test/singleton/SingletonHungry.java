package singleton;

/**
 * 饿汉式
 *
 * @author Tanlian
 * @create 2018-08-17 11:09
 **/
public class SingletonHungry {

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return instance;
    }
}
