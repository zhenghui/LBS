package zhenghui.geo;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: 下午8:45
 * 接收的屌丝
 */
public class Diaosi extends Positioner {
    private static final long serialVersionUID = -6171337063594958434L;

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
