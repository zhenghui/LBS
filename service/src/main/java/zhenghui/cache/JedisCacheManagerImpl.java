package zhenghui.cache;


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import zhenghui.util.SerializeUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * User: zhenghui
 * Date: 15-1-17
 * Time: 上午10:41
 * <p>
 * 使用redis 实现 {@link zhenghui.cache.CacheManager}
 * redis的客户端是jedis。
 * 这个实现只是为了演示，所以对redis操作本身没有做优化。如果有需要可以自己实现对应的CacheManager.
 * </p>
 * <p>
 * 因为需要实现prefixget 和prefixput的功能。这里的实现是通过 pkey+skey作为redis存储的key
 * 这样地话 put就很容易，设置对应的过期时间也很容易。
 * 只有在prefixget的时候，需要通过 {@link redis.clients.jedis.BinaryJedis#keys(byte[])} 获取到所有符合的keys
 * 然后遍历所有的key，获取到所有的值。
 * </p>
 * 时间复杂度是 O(n)
 *
 */
public class JedisCacheManagerImpl implements CacheManager{

    private Logger logger = LoggerFactory.getLogger(JedisCacheManagerImpl.class);

    /**
     * 直接在本机安装了redis服务端.
     * 具体的安装方法见 <a href="http://www.redis.cn/download.html>
     * 只是演示，就不做优化和弄链接池了。
     */
    private static Jedis jedis = new Jedis("localhost");

    @Override
    public boolean prefixPut(String pkey, String skey, Serializable value, int expireTime) {
        if(pkey == null || skey == null){
            return Boolean.FALSE;
        }
        String key = pkey + skey;
        jedis.set(key.getBytes(),SerializeUtil.serialize(value));
        jedis.expire(key,expireTime);
        return Boolean.TRUE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Serializable get(String pkey, String skey) {
        if(pkey == null || skey == null){
            return null;
        }
        try{
//            jedis.keys("");
//            byte[] value_bytes = jedis.get(pkey.getBytes());
//            if(value_bytes == null || value_bytes.length == 0){
//                return null;
//            }
//            Map<String,Serializable> map = (Map<String, Serializable>) SerializeUtil.unserialize(value_bytes);
//            if(map == null){
//                return null;
//            }
//            return map.get(skey);
            String key = pkey+skey;
            return (Serializable) SerializeUtil.unserialize(jedis.get(key.getBytes()));
        }catch (Exception e){
            logger.error("zhenghui.cache.JedisCacheManagerImpl.get error.",e);
        }
        return null;
    }

    @Override
    public void invalid(String pkey, String skey) {
        String key = pkey + skey;
        jedis.del(key);
    }

    @Override
    public List<Serializable> prefixGets(String pkey,int limit) {
        Set<String> keySet = jedis.keys(pkey+"*");
        List<Serializable> list = Lists.newArrayList();
        if(limit <= 0){
            return list;
        }
        for(String key : keySet){
            list.add((Serializable) SerializeUtil.unserialize(jedis.get(key.getBytes())));
            if(list.size() >= limit){
                return list;
            }
        }
        return list;
    }
}
