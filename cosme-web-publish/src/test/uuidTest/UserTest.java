package uuidTest;

import org.junit.Test;

import java.util.UUID;

/**
 * @author Tanlian
 * @create 2018-08-19 19:25
 **/
public class UserTest {

    @Test
    public void test() {

        for (int i = 0; i < 10 ; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
        }
        System.out.println("---------");
        String s = UUID.randomUUID().toString();
        String s1 = s.replace("-", "");
        System.out.println(s1);
        System.out.println(s);


    }
}
