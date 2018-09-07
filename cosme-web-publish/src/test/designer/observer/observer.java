package designer.observer;

/**
 * 抽象观察者
 * @author Tanlian
 * @create 2018-09-05 21:57
 **/
public interface observer {

    /**
     * 当观察者调用notifyObservers()方法时，观察者的update()方法会被回调
     * @param message
     */
    public void update(String message);

}
