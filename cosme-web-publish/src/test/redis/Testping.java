package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

/**
 * @author Tanlian
 * @create 2018-08-20 23:03
 **/
public class Testping {

    @Test
    public void test() {
        Jedis jedis = new Jedis("192.168.20.10", 6379);
        System.out.println(jedis.ping());
        jedis.set("key1", "kjkjhkjjkjkj");
        jedis.set("s2", "b");
        String key1 = jedis.get("key1");
        System.out.println(key1);
        Long ttl = jedis.ttl("key1");
        System.out.println(ttl);
    }

    @Test
    public void transactionTest() {
        Jedis jedis = new Jedis("192.168.20.10", 6379);
        Transaction transaction = jedis.multi();
        transaction.watch("k1");
        transaction.set("k1", "abcd");
        transaction.set("k1", "12390");
        transaction.set("k2", "45678");
        transaction.exec();
    }

    @Test
    public void transactionTest2() {
        Jedis jedis = new Jedis("192.168.20.10", 6379);
        int balance;
        int debt;
        int spend = 10;
        balance = Integer.parseInt(jedis.get("balance"));
         debt = Integer.parseInt(jedis.get("debt"));
        Transaction transaction = jedis.multi();
        jedis.watch("balance");
        if (balance < spend) {
            jedis.unwatch();
            System.out.println("modify");
        }else  {
            transaction.decrBy("balance", spend);
            transaction.incrBy("debt", spend);
            transaction.exec();
        }
        System.out.println(balance);
        System.out.println(debt);
    }
    @Test
    public void msTest3() {
        Jedis jedisM = new Jedis("192.168.20.10", 6379);
        Jedis jedisS = new Jedis("192.168.20.10", 6380);
        jedisM.set("StaticTest","A001");
        jedisS.slaveof("192.168.20.10", 6379);
        System.out.println(jedisS.get("StaticTest"));
    }

    @Test
    public void poolTest() {
        JedisPool jedisPool = JedisPoolUtil.getJedisPlloInstance();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("class", "12345678");
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool, jedis);
        }
    }



}
