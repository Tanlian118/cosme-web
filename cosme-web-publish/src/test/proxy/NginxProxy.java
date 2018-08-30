package proxy;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

/**
 * 静态代理类
 * @author Tanlian
 * @create 2018-08-29 20:06
 **/
public class NginxProxy implements Server {

    private Server server;

    public NginxProxy(Server server) {
        this.server = server;
    }


    private static final List<String> SINA_SERVER_ADRESS  = Lists.newArrayList("196.168.1.1", "196.169.1.2");

    @Override
    public String getPageTitle(String url) {
        String remoteIp = UUID.randomUUID().toString();
       int index= Math.abs(remoteIp.hashCode()) % SINA_SERVER_ADRESS.size();
        String sinaIp = SINA_SERVER_ADRESS.get(index);
        return "页面标题:"+ server.getPageTitle(url) + "来源Ip:" + sinaIp;
    }
}
