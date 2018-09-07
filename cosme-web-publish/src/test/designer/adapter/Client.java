package designer.adapter;

import org.junit.Test;

/**
 * @author Tanlian
 * @create 2018-09-05 20:57
 **/
public class Client {

    @Test
    public void test() {
        Adaptee adaptee = new Adaptee();
        Adapter adapter = new Adapter(adaptee);
        adapter.reqest();
    }
}
