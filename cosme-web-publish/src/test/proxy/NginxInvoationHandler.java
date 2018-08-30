package proxy;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

/**
 * @author Tanlian
 * @create 2018-08-29 20:37
 **/
public class NginxInvoationHandler implements InvocationHandler {

    private Object object;

    public NginxInvoationHandler(Object object) {
        this.object = object;
    }

    private static final List<String> SINA_SERVER_ADRESS = Lists.newArrayList("196.168.1.1", "196.169.1.2");


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里就简单传了一个url，正常请求传入的是Request，使用UUID模拟请求原始I
        String remoteIp = UUID.randomUUID().toString();
//        路由选择算法这里简单定义为对remoteIp的Hash值的绝对值取模
        int index= Math.abs(remoteIp.hashCode()) % SINA_SERVER_ADRESS.size();
        String sinaIp = SINA_SERVER_ADRESS.get(index);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("页面标题:");
        stringBuilder.append(method.invoke(object, args));
        stringBuilder.append("来源Ip:");
        stringBuilder.append(sinaIp);
        return stringBuilder.toString();
    }
}
