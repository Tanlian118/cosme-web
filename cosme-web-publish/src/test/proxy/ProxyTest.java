package proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tanlian
 * @create 2018-08-29 20:18
 **/

public class ProxyTest {

//    用户不和最终目标对象角色（SinaServer）打交道，而是和代理对象角色（NginxProxy）打交道，
//     由代理对象角色（NginxProxy）控制用户的访问。
    @Test
    public void  staticProxyTest() {
        Server sinaServer = new SinaServer();
        NginxProxy nginxProxy = new NginxProxy(sinaServer);
        String pageTitle = nginxProxy.getPageTitle("http://www.sina.com.cn");
        System.out.println(pageTitle);
    }

//    动态代理有一个最大的缺点，就是它只能针对接口生成代理，不能只针对某一个类生成代理，
// 比方说我们在调用Proxy的newProxyInstance方法的时候，第二个参数传某个具体类的getClass()
    @Test
    public void DynamicProxyTest() {
        SinaServer sinaServer = new SinaServer();
        NginxInvoationHandler invoationHandler = new NginxInvoationHandler(sinaServer);
        Server proxyInstance = (Server) Proxy.newProxyInstance(invoationHandler.getClass().getClassLoader(), new Class[]{Server.class}, invoationHandler);
        System.out.println(this.getClass().getClassLoader());
        System.out.println(invoationHandler.getClass().getClassLoader());
        System.out.println(sinaServer.getClass().getClassLoader());
        System.out.println(proxyInstance.getPageTitle("http://www.sina.com.cn"));
        new LinkedHashMap<>();
        new ConcurrentHashMap<>();
    }

}
