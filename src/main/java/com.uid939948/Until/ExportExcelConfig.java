package com.uid939948.Until;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ExportExcelConfig implements Serializable {
    private long uid;

    private int port;

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("export.ruid", this.uid);
        return map;
    }

}
