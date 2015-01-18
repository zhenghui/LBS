package zhenghui.service.diaosi;

import zhenghui.geo.Diaosi;
import zhenghui.service.diaosi.result.CancelResult;
import zhenghui.service.diaosi.result.LoopResult;
import zhenghui.service.diaosi.result.RegisterResult;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: 下午8:48
 *  接收者（屌丝）的服务
 */
public interface DiaosiService {

    /**
     * 注册接收者
     */
    public RegisterResult register(Diaosi diaosi);

    /**
     * 轮询接口
     */
    public LoopResult loop(Diaosi diaosi);

    /**
     * 取消接口
     */
    public CancelResult cancel(Diaosi diaosi);
}
