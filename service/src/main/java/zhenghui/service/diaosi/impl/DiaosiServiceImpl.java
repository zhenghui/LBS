package zhenghui.service.diaosi.impl;

import zhenghui.cache.CacheManager;
import zhenghui.geo.Diaosi;
import zhenghui.service.diaosi.DiaosiService;
import zhenghui.service.diaosi.result.CancelResult;
import zhenghui.service.diaosi.result.LoopResult;
import zhenghui.service.diaosi.result.RegisterResult;

import javax.annotation.Resource;

/**
 * User: zhenghui
 * Date: 15-1-18
 * Time: ÏÂÎç9:03
 */
public class DiaosiServiceImpl implements DiaosiService {

    @Resource
    private CacheManager cacheManager;

    @Override
    public RegisterResult register(Diaosi diaosi) {
        return null;
    }

    @Override
    public LoopResult loop(Diaosi diaosi) {
        return null;
    }

    @Override
    public CancelResult cancel(Diaosi diaosi) {
        return null;
    }
}
