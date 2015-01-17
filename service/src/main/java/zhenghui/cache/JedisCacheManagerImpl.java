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
 * Time: ����10:41
 * <p>
 * ʹ��redis ʵ�� {@link zhenghui.cache.CacheManager}
 * redis�Ŀͻ�����jedis��
 * ���ʵ��ֻ��Ϊ����ʾ�����Զ�redis��������û�����Ż����������Ҫ�����Լ�ʵ�ֶ�Ӧ��CacheManager.
 * </p>
 * <p>
 * ��Ϊ��Ҫʵ��prefixget ��prefixput�Ĺ��ܡ������ʵ����ͨ�� pkey+skey��Ϊredis�洢��key
 * �����ػ� put�ͺ����ף����ö�Ӧ�Ĺ���ʱ��Ҳ�����ס�
 * ֻ����prefixget��ʱ����Ҫͨ�� {@link redis.clients.jedis.BinaryJedis#keys(byte[])} ��ȡ�����з��ϵ�keys
 * Ȼ��������е�key����ȡ�����е�ֵ��
 * </p>
 * ʱ�临�Ӷ��� O(n)
 *
 */
public class JedisCacheManagerImpl implements CacheManager{

    private Logger logger = LoggerFactory.getLogger(JedisCacheManagerImpl.class);

    /**
     * ֱ���ڱ�����װ��redis�����.
     * ����İ�װ������ <a href="http://www.redis.cn/download.html>
     * ֻ����ʾ���Ͳ����Ż���Ū���ӳ��ˡ�
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
