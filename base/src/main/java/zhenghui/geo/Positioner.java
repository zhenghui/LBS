package zhenghui.geo;

import zhenghui.util.GEOUtil;
import zhenghui.util.NumberUtil;

import java.io.Serializable;

/**
 * User: zhenghui
 * Date: 13/12/14
 * Time: 16:52
 * �ɱ��λ�õĶ���
 */
public abstract class Positioner implements Serializable{
    private static final long serialVersionUID = 7018184189935766781L;

    /**
     * ���� longitude
     */
    private Long lon;
    /**
     * γ�� latitude
     */
    private Long lat;

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    /**
     * �����������ӡ�
     * ��С��������4.42ƽ�����ס������Ҫ����40ƽ������Ϊһ��������ô��Ӧ����������Ӧ�þ���
     * 40ƽ������/4.42ƽ������ Լ���� 10^12 ��
     * ������������Ӹ��ݵ�ʱ�������Լ�ȷ����
     */
    protected abstract long getAreaFactor();

    /**
     * λ��hash
     * ��������5.1��ƽ������ = 5000000000000000000 ƽ������
     * ����geo hash���ֳ� 4^30������
     * ��С����������4.42ƽ�����ס�
     */
    public Long getGeoHash() {
        if(NumberUtil.isNull(lon) || NumberUtil.isNull(lat)){
            return 0l;
        }
        return GEOUtil.encode(lon, lat);
    }

    /**
     * ��Ӧ������ID
     */
    public long getAreaId(){
        return getGeoHash()/getAreaFactor();
    }
}
