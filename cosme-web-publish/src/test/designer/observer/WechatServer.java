package designer.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * 被观察者，也就是微信公众号服务
 * 对Observerable接口的三个方法做实现
 * @author Tanlian
 * @create 2018-09-05 22:00
 **/
public class WechatServer implements Observerable {

    private List<Observer> list;
    private String message;
    private User user;

    public WechatServer() {
        list  =new ArrayList<Observer>();
    }

    @Override
    public void regiterObserver(Observer observer) {
        list.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!list.isEmpty()) {
            list.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update(null,message);
        }
    }

    public void setInformation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息" + s);
//        消息更新，通知所有观察者
        notifyObservers();
    }
}
