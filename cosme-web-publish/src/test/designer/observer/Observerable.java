package designer.observer;

import java.util.Observer;

/**
 * 抽象观察者接口
 * 声明添加、删除、通知观察者方法
 * @author Tanlian
 * @create 2018-09-05 21:23
 **/
public interface Observerable {

    public void regiterObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();

}

