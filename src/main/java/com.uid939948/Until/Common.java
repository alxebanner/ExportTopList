package com.uid939948.Until;

import com.alibaba.fastjson.JSONObject;
import com.uid939948.DO.testDo.ExportList;
import com.uid939948.DO.testDo.JsonRootBean;
import com.uid939948.DO.testDo.List1;
import com.uid939948.Excel.ExcelUtils;
import com.uid939948.HttpUtil.HttpUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class Common {
//    public static final Properties p = new Properties();
//    public static final String path = "file.properties";

    /**
     * 查询配置
     *
     * @return UID
     */
//    public static long findConfig() {
//        Properties properties = new Properties();
//        File file = new File(System.getProperty("user.dir") + File.separator + "export.properties");
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//            properties.load(inputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return Long.parseLong((String) properties.get("export.ruid"));
//    }

//    @Bean

    /**
     * 获取基础配置
     *
     * @return 基础配置
     */
    public static ExportExcelConfig findConfig() {
        Properties properties = new Properties();
        File file = new File(System.getProperty("user.dir") + File.separator + "export.properties");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ExportExcelConfig exportExcelConfig = new ExportExcelConfig();
        exportExcelConfig.setUid(Long.parseLong((String) properties.get("export.ruid")));
        return exportExcelConfig;
    }

    /**
     * 同归UID查询 不重复的舰长列表
     *
     * @param ruid UID
     * @return 舰长列表
     */
    public static List<ExportList> getTopList(String ruid) {
        String url = "https://api.live.bilibili.com/xlive/app-room/v2/guardTab/topList?" +
                "roomid=1" +
                "&page=1&" +
                "ruid=" + ruid +
                "&page_size=29";
        String getResult = HttpUtil.doGet(url);

        JsonRootBean gameList1 = JSONObject.parseObject(getResult, JsonRootBean.class);
        int int12 = gameList1.getData().getInfo().getPage();

        String sendStr1 = "https://api.live.bilibili.com/xlive/app-room/v2/guardTab/topList?" +
                "roomid=1" +
                "&ruid=" + ruid +
                "&page_size=29";
        List<List1> top3 = gameList1.getData().getTop3();
        List<List1> list1 = new ArrayList<>();
        if (int12 == 1) {
            list1.addAll(gameList1.getData().getList());
        } else {
            for (int i = 1; i <= int12; i++) {
                String a1 = sendStr1 + "&page=" + i;
                String getResult1 = HttpUtil.doGet(a1);
                JsonRootBean gameList2 = JSONObject.parseObject(getResult1, JsonRootBean.class);
                list1.addAll(gameList2.getData().getList());
            }
        }
        // uid去重
        List<List1> unique = list1.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(List1::getUid))), ArrayList::new)
        );
        unique.addAll(top3);

        List<ExportList> listNow = unique.stream().map(mo -> {
            ExportList exportList_temp = new ExportList();
            exportList_temp.setRank(mo.getRank());
            exportList_temp.setGuard_level(mo.getGuard_level());
            exportList_temp.setUid(mo.getUid());
            exportList_temp.setMedal_level(mo.getMedal_info().getMedal_level());
            exportList_temp.setUsername(mo.getUsername());
            return exportList_temp;
        }).collect(Collectors.toList());
        return listNow;
    }


    /**
     * 通过UID获取用户名
     *
     * @param ruid
     * @return
     */
    public static String getUserNameByUid(String ruid) {
        String url = " https://api.bilibili.com/x/space/acc/info?mid=" + ruid;

        Map<String, String> headers = null;
        headers = new HashMap<>(3);
        headers.put("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");

        headers.put("referer", "https://space.bilibili.com/" + ruid);
        String getResult = HttpUtil.doGetWithHeader(url, headers);

        String name = JSONObject.parseObject(getResult).getJSONObject("data").getString("name");

        return name;
    }

    public void exportExcel(List<ExportList> listNow, String ruid) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); // 设置日期格式
        String fileName = System.getProperty("user.dir") + File.separator + "uid " + ruid + "--" + df.format(new Date()) + ".xls";
        ExcelUtils.createNewExcel(fileName);
        try {
            String titles[] = {"uid", "排名", "昵称", "舰队等级", "勋章等级"};
            // 输出到的文件
            FileOutputStream fout = new FileOutputStream(fileName);
            // 如若在servlet中返回excel则填写 ExcelUtils.createExcel(users,titles,response.getOutputStream());
            // 并设置contentType为 application/vnd.ms-excel
            ExcelUtils.createExcel(listNow, titles, fout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//
//    /**
//     * 修改配置文件
//     *
//     * @param key   更新的key
//     * @param value 更新的值
//     */
//    public static void update(String key, String value) {
//        Properties p = new Properties();
//        p.setProperty(key, value);
//        FileOutputStream oFile = null;
//        try {
//            oFile = new FileOutputStream(path);
//            //将Properties中的属性列表（键和元素对）写入输出流
//            p.store(oFile, "");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (oFile != null) {
//                    oFile.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void updateProperties(String key, String value) {

        Properties pro = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("属性文件路径"));
            pro.load(in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //重新写入配置文件
        try {
            FileOutputStream file = new FileOutputStream("配置文件路径");

            pro.put("LOCAL_USER", "");
            pro.put("LOCAL_PWD", "");
            System.out.println("得到属性key:" + pro.getProperty("LOCAL_PWD"));
            pro.store(file, "系统配置修改"); //这句话表示重新写入配置文件
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean writeProperties1111(ExportExcelConfig exportExcelConfig, String propertiesFilePath) {
        Properties properties = new Properties();
        Map<String, Object> map = exportExcelConfig.getMap();

        for (String key : map.keySet()) {
            properties.setProperty(key, map.get(key).toString());
        }
        try {
            OutputStream fos = new FileOutputStream(propertiesFilePath);
            try {
                properties.store(fos, properties.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
}
