package zhenghui.cache;

import org.junit.Test;

/**
 * User: zhenghui
 * Date: 15-1-14
 * Time: ����10:03
 * �yԇredis �͑���
 */
public class RedisCacheTest {

    @Test
    public void test1(){
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
    }
}
