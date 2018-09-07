package designer.observer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Observable;
import java.util.Observer;

/**
 * 定义具体观察者
 * @author Tanlian
 * @create 2018-09-05 22:09
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Observer{

    private String name;
    private String message;

    public  User(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.message = message;
        read();
    }

    private void read() {
        System.out.println("收到推送消息" + message);
    }
}
