package zhenghui.geo;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: ����8:43
 * �ɷ�������
 */
public class Tuhao extends Positioner {
    private static final long serialVersionUID = -5748647099017923513L;

    public void setAreaFactor(long areaFactor) {
        this.areaFactor = areaFactor;
    }

    /**
     * Ĭ�ϵ�����������10��12�Σ������ػ���ÿ����������42ƽ��km
     */
    private long areaFactor = 10 ^ 12;

    @Override
    protected long getAreaFactor() {
        return areaFactor;
    }
}
