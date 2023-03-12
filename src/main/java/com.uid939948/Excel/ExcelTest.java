package com.uid939948.Excel;

import com.alibaba.fastjson.JSONObject;
import com.uid939948.DO.testDo.ExportList;
import com.uid939948.DO.testDo.JsonRootBean;
import com.uid939948.DO.testDo.List1;
import com.uid939948.HttpUtil.HttpUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelTest {
//    public static void main(String[ ] args) {
//        long ruid = 1;
//        Properties properties = new Properties();
//        File file = new File(System.getProperty("user.dir") + File.separator + "export.properties");
//        try {
//            InputStream inputStream = new FileInputStream(file);
//            properties.load(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ruid = Long.parseLong((String) properties.get("export.ruid"));
//
//        String url = "https://api.live.bilibili.com/xlive/app-room/v2/guardTab/topList?" +
//                "roomid=1" +
//                "&page=1&" +
//                "ruid=" + ruid +
//                "&page_size=29";
//        String getResult = HttpUtil.doGet(url);
//
//        JsonRootBean gameList1 = JSONObject.parseObject(getResult, JsonRootBean.class);
//        int int12 = gameList1.getData().getInfo().getPage();
//
//        String sendStr1 = "https://api.live.bilibili.com/xlive/app-room/v2/guardTab/topList?" +
//                "roomid=1" +
//                "&ruid=" + ruid +
//                "&page_size=29";
//        List<List1> top3 = gameList1.getData().getTop3();
//        List<List1> list1 = new ArrayList<>();
//        if (int12 == 1) {
//            list1.addAll(gameList1.getData().getList());
//        } else {
//            for (int i = 1; i <= int12; i++) {
//                String a1 = sendStr1 + "&page=" + i;
//                String getResult1 = HttpUtil.doGet(a1);
//                JsonRootBean gameList2 = JSONObject.parseObject(getResult1, JsonRootBean.class);
//                list1.addAll(gameList2.getData().getList());
//            }
//        }
//        // uid去重
//        List<List1> unique = list1.stream().collect(
//                Collectors.collectingAndThen(
//                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(List1::getUid))), ArrayList::new)
//        );
//        unique.addAll(top3);
//
//        List<ExportList> listNow = unique.stream().map(mo -> {
//            ExportList exportList_temp = new ExportList();
//            exportList_temp.setRank(mo.getRank());
//            exportList_temp.setGuard_level(mo.getGuard_level());
//            exportList_temp.setUid(mo.getUid());
//            exportList_temp.setMedal_level(mo.getMedal_info().getMedal_level());
//            exportList_temp.setUsername(mo.getUsername());
//            return exportList_temp;
//        }).collect(Collectors.toList());
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); // 设置日期格式
//        String fileName = System.getProperty("user.dir") + File.separator + "uid " + ruid + "--" + df.format(new Date()) + ".xls";
//        ExcelUtils.createNewExcel(fileName);
//        try {
//            String titles[] = {"uid", "排名", "昵称", "舰队等级", "勋章等级"};
//            // 输出到的文件
//            FileOutputStream fout = new FileOutputStream(fileName);
//            // 如若在servlet中返回excel则填写 ExcelUtils.createExcel(users,titles,response.getOutputStream());
//            // 并设置contentType为 application/vnd.ms-excel
//            ExcelUtils.createExcel(listNow, titles, fout);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}

