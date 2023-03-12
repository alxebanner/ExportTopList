package com.uid939948.Excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ExcelUtils {
    public static void createExcel(List dataLists, String[] titles, OutputStream out) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFCellStyle titleCellStyle = workbook.createCellStyle();

        HSSFDataFormat format = workbook.createDataFormat();
        titleCellStyle.setDataFormat(format.getFormat("@"));

        //设置单元标题样式
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleCellStyle.setFillForegroundColor(HSSFColorPredefined.SKY_BLUE.getIndex());
        titleCellStyle.setWrapText(true);
        //设置单元标题字体
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 13);
        titleCellStyle.setFont(titleFont);

        //填写标题
        HSSFRow headRow = sheet.createRow(0);
        HSSFCell headerCell = null;
        for (int i = 0; i < titles.length; i++) {
            headerCell = headRow.createCell(i);
            headerCell.setCellStyle(titleCellStyle);
            headerCell.setCellValue(titles[i]);
            sheet.setColumnWidth(i, (30 * 160));
        }

        //设置表格内容单元样式
        HSSFCellStyle valueCellStyle = workbook.createCellStyle();
        valueCellStyle.setAlignment(HorizontalAlignment.CENTER);
        valueCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont cellFont = workbook.createFont();
        cellFont.setFontHeightInPoints((short) 12);
        valueCellStyle.setFont(cellFont);

        for (int i = 0; i < dataLists.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Class clazz = dataLists.get(i).getClass();
            Field fields[] = clazz.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                String fieldName = fields[j].getName();
                fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                //执行get方法，获取属性
                Method gMethod;
                try {
                    gMethod = clazz.getMethod("get" + fieldName);
                    String value = gMethod.invoke(dataLists.get(i)).toString();
                    if (value == null) {
                        value = "";
                    }
                    HSSFCell valueCell = row.createCell(j);

                    valueCell.setCellStyle(valueCellStyle);
                    valueCell.setCellType(CellType.STRING);
                    valueCell.setCellValue(value);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();

                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 导出成功!
    }

    /**
     *
     *
     * @param fileName 文件名字
     */
    public static void createNewExcel(String fileName) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        try {
            File file = new File( fileName );
            FileOutputStream out = new FileOutputStream(file);
//            FileOutputStream out = new FileOutputStream(
//                    new File("C:/poiexcel/createBlankWorkBook.xlsx"));
            //write operation workbook using file out object
            hssfWorkbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("createworkbook.xlsx written successfully");
    }
}



