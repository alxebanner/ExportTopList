package com.uid939948.DO.VO;

import com.uid939948.DO.testDo.ExportList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExcelVo {
    /**
     * 地址
     */
    private String path;

    private List<ExportList> list;
}
