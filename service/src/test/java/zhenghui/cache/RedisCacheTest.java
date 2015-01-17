package zhenghui.cache;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import zhenghui.util.SerializeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: zhenghui
 * Date: 15-1-14
 * Time: ÏÂÎç10:03
 * œyÔ‡redis ¿Í‘ô¶Ë
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
        System.out.println(jedis.llen("list"));
        System.out.println(jedis.lrange("list", 0, 3));
        jedis.expire("list",5);
        try {
            Thread.sleep(6000l);
        } catch (InterruptedException ignored) {

        }
        System.out.println(jedis.lrange("list", 0, 3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPrefixGet(){

//        Map<String,String> map = new HashMap<String, String>(){
//            {
//                put("key1","value1");
//                put("key2","value2");
//                put("key3","value3");
//            }
//
//            private static final long serialVersionUID = -5278020639813324497L;
//        };
        Map<String,String> map = new HashMap<String, String>();
        map.put("key1","value1");

        byte[] bytes = SerializeUtil.serialize(map);
        System.out.println(bytes.length);
        String prefixKey = "prefixKey";
        jedis.set(prefixKey.getBytes(), bytes);
        Map<String,String> mapValue = (Map<String, String>) SerializeUtil.unserialize(jedis.get(prefixKey.getBytes()));
        System.out.println(mapValue);
    }

    @Test
    public void testPrefixGetByKeys(){
        Foo foo1 = new Foo().setName("foo1");
        Foo foo11 = new Foo().setName("foo11");
        Foo foo111 = new Foo().setName("foo111");
        Foo foo2 = new Foo().setName("foo2");

        jedis.set("foo1".getBytes(),SerializeUtil.serialize(foo1));
        jedis.set("foo11".getBytes(),SerializeUtil.serialize(foo11));
        jedis.set("foo111".getBytes(),SerializeUtil.serialize(foo111));
        jedis.set("foo2".getBytes(),SerializeUtil.serialize(foo2));


        Set<String> key_set = jedis.keys("foo1*");
        System.out.println(key_set);

        for(String key : key_set){
            System.out.println(SerializeUtil.unserialize(jedis.get(key.getBytes())));
        }

    }


}
