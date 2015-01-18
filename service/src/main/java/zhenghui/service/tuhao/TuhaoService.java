package zhenghui.service.tuhao;

import zhenghui.geo.Tuhao;
import zhenghui.service.diaosi.result.CancelResult;
import zhenghui.service.tuhao.result.CommitResult;
import zhenghui.service.tuhao.result.LoopResult;
import zhenghui.service.tuhao.result.ShareResult;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: 下午9:05
 * 派发者的服务（土豪）
 */
public interface TuhaoService {

    /**
     * 派发
     */
    public ShareResult share(Tuhao tuhao);

    /**
     * 轮询接口
     */
    public LoopResult loop(Tuhao tuhao);

    /**
     * 取消
     */
    public CancelResult cancel(Tuhao tuhao);

    /**
     * 提交
     */
    public CommitResult commit(Tuhao tuhao);

}
