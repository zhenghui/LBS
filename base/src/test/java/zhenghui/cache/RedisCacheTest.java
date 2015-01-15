package zhenghui.cache;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * User: zhenghui
 * Date: 15-1-14
 * Time: ÏÂÎç10:03
 * œyÔ‡redis ¿Í‘ô¶Ë
 */
public class RedisCacheTest {

    @Test
    public void test1(){
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }
}
