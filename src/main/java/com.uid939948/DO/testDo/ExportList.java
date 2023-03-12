package com.uid939948.DO.testDo;

import lombok.Data;

/**
 * excel中打印的数据
 */
@Data
public class ExportList {
    /**
     * uid号码
     */
    private long uid;
    /**
     * 用户排名
     */
    private int rank;
    /**
     * 用户名
     */
    private String username;
    /**
     * 舰队等级
     */
    private int guard_level;
    /**
     * 勋章等级
     */
    private String medal_level;
}
