package zhenghui.cache;

import java.io.Serializable;
import java.util.List;

/**
 * User: zhenghui
 * Date: 12/01/15
 * Time: 20:10
 * ���������Ҫʵ�ֵĹ�����ǰ׺put����ǰ׺get��Ӧ�����ݡ�
 * ����ʵ������ӿڵ�ʵ���࣬������֧�ִ��������͸߲�����һ�㶼�ǻ���kv��������ʵ��
 * ���ﶨ����һ���ӿڡ������ʵ�ֿ����Լ�ѡ�񣬱���redis������ mongodb ��
 */
public interface CacheManager {

    /**
     * ǰ׺put
     * @param pkey/skey ��/��key
     * @param value ֵ
     * @param expireTime ����ʱ�䣬��λ��
     * @return true �ɹ� false ʧ��
     */
    public boolean prefixPut(Serializable pkey, Serializable sKey, Serializable value, int expireTime);

    /**
     *
     * ���� ����key ����ȡ��Ӧ�Ķ���
     * @param pkey ��key
     * @param skey ��key
     * @return �������
     */
    public Serializable get(Serializable pkey,Serializable skey);


    /**
     * ���� ����key ��ɾ����Ӧ�Ķ���
     * @param pkey ��key
     * @param skey ��key
     */
    public void invalid(Serializable pkey,Serializable skey);

    /**
     * ǰ׺gets
     * @param pkey ��key
     * @param start ��ʼ������Ĭ��0����һ��
     * @param limit ���һ���Ի�ȡ����
     * @return �����б�
     */
    public List<Serializable> prefixGets(Serializable pkey,int start, int limit);


}
