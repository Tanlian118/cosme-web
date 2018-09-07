package designer.observer;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-09-05 22:14
 **/
public class ObserverTest {

    @Test
    public void test() {
        WechatServer wechatServer = new WechatServer();
        User userZhang = new User("zhangsan");
        User userLi = new User("lisi");
        User userWang = new User("wangwu");

        wechatServer.regiterObserver(userZhang);
        wechatServer.regiterObserver(userLi);
        wechatServer.regiterObserver(userWang);
        wechatServer.setInformation(":你为什么学java");

        System.out.println("------------------------");
        wechatServer.removeObserver(userWang);
        wechatServer.setInformation(":哈哈哈哈哈哈哈");
    }
}
