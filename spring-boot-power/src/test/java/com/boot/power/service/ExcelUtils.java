package com.boot.power.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * Created by PC-007 on 2019/11/15.
 */
public class ExcelUtils {

    public static XSSFWorkbook createWork(){
        return new XSSFWorkbook();
    }


    public static void excelTemplate(HttpServletResponse response) throws IOException {


        // 创建word
        XSSFWorkbook xssfWorkbook = createWork();
        // 创建sheet
        XSSFSheet sheet = xssfWorkbook.createSheet("参考模板");

        //设置背景色
        XSSFCellStyle style = xssfWorkbook.createCellStyle();
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());

        // 设置列宽动态设置吧。
//        sheet.setColumnWidth(0, 23*24);
        // 初始化测试数据
        String[] companyInfoName = {"公司id", "公司名词", "任务类型", "任务总额"};
        String[] taskInfoName = {"任务id", "任务名称", "任务时间"};
        String[] infoName = {"日期", "任务基数", "每日份额", "每日总额"};
        // 设置各个名称下的数据
        String[] companyInfo = {"1001", "name", "type"};
        String[] taskInfo = {"2001", "task", "2019/1/1-2019/2/1"};
        String time = "2019/1/";
        // 定义多少个公司
        int conNum = 2;
        // 定义各个公司的间隔列,以第一列的距离为准，距离第一列多少是第二个公司的信息
        int division = 5;

        // 定义要创建的标题行数
        int bNum = 3;
        // 设置公司名称
        // 创建行号
        Row row = sheet.createRow(0);
        for (int i = 0; i < companyInfoName.length; i++) {
            // 定义第一条
            int num = 0;
            while (num < conNum) {
                Cell cell = row.createCell(i + (division * num));
                cell.setCellValue(companyInfoName[i]);
                cell.setCellStyle(style);
                num++;
            }
        }
        // 设置任务信息
        // 创建第二个标题行
        row = sheet.createRow(2);
        for (int i = 0; i < taskInfoName.length; i++) {
            // 定义第一条
            int num = 0;
            while (num < conNum) {
                Cell cell = row.createCell(i + (division * num));
                cell.setCellValue(taskInfoName[i]);
                cell.setCellStyle(style);
                num++;
            }
        }
        // 设置内容标题
        // 创建第三个标题
        row = sheet.createRow(4);
        for (int i = 0; i < infoName.length; i++) {
            // 定义第一条
            int num = 0;
            while (num < conNum) {
                Cell cell = row.createCell(i + (division * num));
                cell.setCellValue(infoName[i]);
                cell.setCellStyle(style);
                num++;
            }
        }


        // 填充数据
        // 填充公司信息
        row = sheet.createRow(1);
        for (int i = 0; i < companyInfo.length; i++) {
            // 定义第一条
            int num = 0;
            while (num < conNum) {
                Cell cell = row.createCell(i + (division * num));
                cell.setCellValue(companyInfo[i]);
                num++;

            }
        }
        // 填充任务信息
        row = sheet.createRow(3);
        for (int i = 0; i < taskInfo.length; i++) {
            // 定义第一条
            int num = 0;
            while (num < conNum) {
                Cell cell = row.createCell(i + (division * num));
                cell.setCellValue(taskInfo[i]);
                num++;

            }
        }
        // 填充其他信息
        int rowNum = 5;
        for (int i = 0; i < 7; i++) {
            row = sheet.createRow(rowNum);
            // 定义第一条
            int num = 0;
            while (num < conNum) {
                Cell cell = row.createCell(0 + (division * num));
                cell.setCellValue(time+i+1);
                num++;

            }
            rowNum++;
        }

        // 生成本地文件
        OutputStream fileOut = null;
        File file = new File("D:"+File.pathSeparator+"excel"+File.pathSeparator+"test.xlsx");
        File fileParent = file.getParentFile();
        if(fileParent.exists()){
            fileParent.mkdirs();
        }

        fileOut = new FileOutputStream(file);

        xssfWorkbook.write(fileOut);
        fileOut.close();
        System.out.print("excel生成成功");
    }

  public static void main(String[] args) throws IOException {
    excelTemplate(null);
  }
}
