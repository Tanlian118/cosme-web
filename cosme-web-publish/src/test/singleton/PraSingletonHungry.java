package singleton;

/**
 * 饿汉式
 * @author Tanlian
 * @create 2018-09-07 22:24
 **/
public class PraSingletonHungry {

    private static PraSingletonHungry instance = new PraSingletonHungry();

    public PraSingletonHungry(){}

    public static PraSingletonHungry getInstance() {
        return instance;
    }
}
