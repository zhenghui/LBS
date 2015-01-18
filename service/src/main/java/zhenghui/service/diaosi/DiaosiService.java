package zhenghui.service.diaosi;

import zhenghui.geo.Diaosi;
import zhenghui.service.diaosi.result.CancelResult;
import zhenghui.service.diaosi.result.LoopResult;
import zhenghui.service.diaosi.result.RegisterResult;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: ����8:48
 *  �����ߣ���˿���ķ���
 */
public interface DiaosiService {

    /**
     * ע�������
     */
    public RegisterResult register(Diaosi diaosi);

    /**
     * ��ѯ�ӿ�
     */
    public LoopResult loop(Diaosi diaosi);

    /**
     * ȡ���ӿ�
     */
    public CancelResult cancel(Diaosi diaosi);
}
