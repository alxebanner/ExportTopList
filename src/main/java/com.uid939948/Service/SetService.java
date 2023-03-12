package com.uid939948.Service;

import com.uid939948.DO.VO.ExcelVo;

public interface SetService {
    /**
     * 初始化项目
     */
    void init();

    /**
     * 初始化时 获取配置里面的UID
     *
     * @return 获取配置项里面的UID
     */
    long getConfigUid();

    /**
     * 获取舰长列表
     *
     * @param uid 用户UID
     * @return 返会列表
     */
    ExcelVo sendConfigUid(String uid);

    /**
     * 保存配置项目
     *
     * @param uid 用户UID
     * @return 是否成功
     */
    Boolean saveConfigUid(String uid);
}
