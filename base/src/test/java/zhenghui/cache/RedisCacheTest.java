package zhenghui.cache;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * User: zhenghui
 * Date: 15-1-14
 * Time: ����10:03
 * �yԇredis �͑���
 */
public class RedisCacheTest {

    private static Jedis jedis;

    @BeforeClass
    public static void beforeClass(){
        jedis = new Jedis("localhost");
    }

    @AfterClass
    public static void afterClass(){
        jedis.close();
    }

    @Before
    public void before(){
        jedis.flushDB();
    }

    @Test
    public void testGet(){
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }

    @Test
    public void testList(){
        jedis.lpush("list", "list1","list2","list3");
        // ���鳤��
        System.out.println(jedis.llen("list"));
        // �ִ�
        System.out.println(jedis.lrange("list", 0, 3));
    }


}
