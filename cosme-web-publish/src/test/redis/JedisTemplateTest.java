package redis;

import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Tanlian
 * @create 2018-08-29 23:15
 **/
public class JedisTemplateTest {

    @Test
    public void test() {
        RedisTemplate<Integer, String> redisTemplate = new RedisTemplate<>();
    }
}
