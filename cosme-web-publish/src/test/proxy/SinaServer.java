package proxy;

/**
 * @author Tanlian
 * @create 2018-08-29 20:04
 **/
public class SinaServer implements Server {

    @Override
    public String getPageTitle(String url) {
        if ("http://www.sina.com.cn".equals(url)) {
            return "新浪首页";
        }
        else if ("http://www.baidu.com".equals(url)) {
            return "百度首页";
        }

        return "无标题";
    }
}
