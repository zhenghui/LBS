package zhenghui.cache;

import java.io.Serializable;
import java.util.List;

/**
 * User: zhenghui
 * Date: 12/01/15
 * Time: 20:10
 * 缓存管理。主要实现的功能是前缀put或者前缀get对应的数据。
 * 所有实现这个接口的实现类，必须能支持大吞吐量和高并发。一般都是基于kv缓存框架来实现
 * 这里定义了一个接口。具体的实现可以自己选择，比如redis，或者 mongodb 等
 */
public interface CacheManager {

    /**
     * 前缀put
     * @param pkey/skey 主/子key
     * @param value 值
     * @param expireTime 过期时间，单位秒
     * @return true 成功 false 失败
     */
    public boolean prefixPut(Serializable pkey, Serializable sKey, Serializable value, int expireTime);

    /**
     *
     * 根据 父子key 来获取对应的对象
     * @param pkey 父key
     * @param skey 子key
     * @return 缓存对象
     */
    public Serializable get(Serializable pkey,Serializable skey);


    /**
     * 根据 父子key 来删除对应的对象
     * @param pkey 父key
     * @param skey 子key
     */
    public void invalid(Serializable pkey,Serializable skey);

    /**
     * 前缀gets
     * @param pkey 父key
     * @param start 开始个数，默认0个第一个
     * @param limit 最多一次性获取几个
     * @return 对象列表
     */
    public List<Serializable> prefixGets(Serializable pkey,int start, int limit);


}
