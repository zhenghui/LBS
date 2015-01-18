package zhenghui.service.tuhao;

import zhenghui.geo.Tuhao;
import zhenghui.service.diaosi.result.CancelResult;
import zhenghui.service.tuhao.result.CommitResult;
import zhenghui.service.tuhao.result.LoopResult;
import zhenghui.service.tuhao.result.ShareResult;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: ����9:05
 * �ɷ��ߵķ���������
 */
public interface TuhaoService {

    /**
     * �ɷ�
     */
    public ShareResult share(Tuhao tuhao);

    /**
     * ��ѯ�ӿ�
     */
    public LoopResult loop(Tuhao tuhao);

    /**
     * ȡ��
     */
    public CancelResult cancel(Tuhao tuhao);

    /**
     * �ύ
     */
    public CommitResult commit(Tuhao tuhao);

}
