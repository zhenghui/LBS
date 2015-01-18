package zhenghui.geo;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: 下午8:43
 * 派发的土豪
 */
public class Tuhao extends Positioner {
    private static final long serialVersionUID = -5748647099017923513L;

    public void setAreaFactor(long areaFactor) {
        this.areaFactor = areaFactor;
    }

    /**
     * 默认的区域因子是10的12次，这样地话，每个区域大概是42平方km
     */
    private long areaFactor = 10 ^ 12;

    @Override
    protected long getAreaFactor() {
        return areaFactor;
    }
}
