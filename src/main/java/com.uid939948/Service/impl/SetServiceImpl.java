package com.uid939948.Service.impl;

import com.uid939948.DO.VO.ExcelVo;
import com.uid939948.DO.testDo.ExportList;
import com.uid939948.Excel.ExcelUtils;
import com.uid939948.Service.SetService;
import com.uid939948.Until.Common;
import com.uid939948.Until.ExportExcelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
@Service
public class SetServiceImpl implements SetService {
    @Lazy
    @Autowired
    private SetService setService;

    @Resource
    private Environment environment;

    @Override
    public void init() {
        // 第一步  加载配置 获取端口号
        String port = environment.getProperty("server.port");
        // 第二部 打开浏览器
        openBrowser(port);
    }

    @Override
    public long getConfigUid() {
        ExportExcelConfig exportExcelConfig = Common.findConfig();
        return exportExcelConfig.getUid();
    }

    @Override
    public ExcelVo sendConfigUid(String uid) {
        String name = Common.getUserNameByUid(uid);
        // 生成列表
        List<ExportList> list = Common.getTopList(uid);
        // 统计总数
        long count = list.stream().count();

        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        // 生成文件夹
        File folder = new File(System.getProperty("user.dir") + File.separator + "uid" + uid + " " + name);
        if (!folder.exists() && !folder.isDirectory()) {
            // mkdirs()可以建立多级文件夹， mkdir()只会建立一级的文件夹
            // mkdir则不会建立任何目录， 因为找不到/目录， 结果返回false
            folder.mkdirs();
            System.out.println("创建文件夹");
        } else {
            System.out.println("文件夹已存在");
        }

        String fileName = System.getProperty("user.dir") + File.separator + "uid" + uid + " " + name + File.separator + "uid " + uid + "--" + df.format(new Date()) + ".xls";
        ExcelUtils.createNewExcel(fileName);
        try {
            String titles[] = {"uid", "排名", "昵称", "舰队等级", "勋章等级"};
            // 输出到的文件
            FileOutputStream fout = new FileOutputStream(fileName);
            // 如若在servlet中返回excel则填写 ExcelUtils.createExcel(users,titles,response.getOutputStream());
            // 并设置contentType为 application/vnd.ms-excel
            ExcelUtils.createExcel(list, titles, fout);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ExcelVo excelVo = ExcelVo.builder()
                .path(fileName)
                .list(list)
                .build();
        return excelVo;
    }

    @Override
    public Boolean saveConfigUid(String uid) {
        try {
            if (StringUtils.isEmpty(uid)) {
                System.out.println("不能为空");
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(uid);
            if (!isNum.matches()) {
                System.out.println("必须为数字");
            }
            Integer i = Integer.valueOf(uid);
        } catch (NumberFormatException e) {
            System.out.println("超过上限");
        }

        System.out.println("用户UID为：" + uid);

        ExportExcelConfig exportExcelConfig = Common.findConfig();
        exportExcelConfig.setUid(Long.parseLong(uid));

        String fileName = System.getProperty("user.dir") + File.separator + "export.properties";

        Common.writeProperties1111(exportExcelConfig, fileName);
        return true;
    }

    /**
     * 打开浏览器
     */
    private void openBrowser(String port) {
        try {
            // 打开浏览器
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:" + port);
        } catch (IOException e) {
            System.out.println(
                    "自动打开浏览器错误:当前系统缺少rundll32 url.dll组件或者不是window系统，无法自动启动默认浏览器打开配置页面，请手动打开浏览器地址栏输入http://127.0.0.1:23333进行配置");
        }
    }

    @Autowired
    @Lazy
    public void setSetService(SetService setService) {
        this.setService = setService;
    }
}
