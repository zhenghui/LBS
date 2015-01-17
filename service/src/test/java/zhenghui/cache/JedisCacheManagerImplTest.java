package zhenghui.cache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class JedisCacheManagerImplTest {

    private JedisCacheManagerImpl jedisCacheManager;

    @Before
    public void setUp() throws Exception {
        jedisCacheManager = new JedisCacheManagerImpl();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPrefixPut() throws Exception {
        Foo foo = createFoo("foo");
        jedisCacheManager.prefixPut("pkey","skey",foo,5);
        Assert.assertTrue(((Foo) jedisCacheManager.get("pkey", "skey")).getName().equals("foo"));
        Thread.sleep(6000);
        Assert.assertNull(jedisCacheManager.get("pkey", "skey"));
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testInvalid() throws Exception {
        Foo foo = createFoo("foo");
        jedisCacheManager.prefixPut("pkey","skey",foo,5);
        Assert.assertTrue(((Foo) jedisCacheManager.get("pkey", "skey")).getName().equals("foo"));
        jedisCacheManager.invalid("pkey","skey");
        Assert.assertNull(jedisCacheManager.get("pkey", "skey"));
    }

    @Test
    public void testPrefixGets() throws Exception {
        Foo foo1 = createFoo("foo1");
        Foo foo11 = createFoo("foo11");
        Foo foo111 = createFoo("foo111");
        Foo foo2 = createFoo("foo2");
        jedisCacheManager.prefixPut("foo","1",foo1,10);
        jedisCacheManager.prefixPut("foo","11",foo11,10);
        jedisCacheManager.prefixPut("foo","111",foo111,10);
        jedisCacheManager.prefixPut("foo","2",foo2,10);
        List<Serializable> list = jedisCacheManager.prefixGets("foo1", 5);
        System.out.println(list);
        Assert.assertEquals(list.size(),3);
        list = jedisCacheManager.prefixGets("foo1", 2);
        System.out.println(list);
        Assert.assertEquals(list.size(),2);
    }

    private Foo createFoo(String name){
        return new Foo().setName(name);
    }
}