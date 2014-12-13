package zhenghui.geo;

import zhenghui.util.GEOUtil;
import zhenghui.util.NumberUtil;

import java.io.Serializable;

/**
 * User: zhenghui
 * Date: 13/12/14
 * Time: 16:52
 * 可标记位置的对象
 */
public abstract class Positioner implements Serializable{
    private static final long serialVersionUID = 7018184189935766781L;

    /**
     * 经度 longitude
     */
    private Long lon;
    /**
     * 纬度 latitude
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
     * 设置区域因子。
     * 最小的区域是4.42平方厘米。如果需要设置40平方公里为一个区域，那么对应的区域因子应该就是
     * 40平方公里/4.42平方厘米 约等于 10^12 。
     * 具体的区域因子根据当时的需求自己确定。
     */
    protected abstract long getAreaFactor();

    /**
     * 位置hash
     * 地球表面积5.1亿平方公里 = 5000000000000000000 平方厘米
     * 经过geo hash划分成 4^30个区域。
     * 最小的区域大概是4.42平方厘米。
     */
    public Long getGeoHash() {
        if(NumberUtil.isNull(lon) || NumberUtil.isNull(lat)){
            return 0l;
        }
        return GEOUtil.encode(lon, lat);
    }

    /**
     * 对应的区域ID
     */
    public long getAreaId(){
        return getGeoHash()/getAreaFactor();
    }
}
