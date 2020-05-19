package com.example.demoController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Description
 * @Date 2019-11-26 09:42
 * @Author ymm
 **/

@RestController
@RequestMapping("/api/down")
@Api("下载excel")
public class TestExcelController {


    @GetMapping("/downTemplate")
    @ApiOperation(value = "下载附件")
    public void getReportByOrgAndMonth(HttpServletResponse response){
        String excelName = "测试";
        try {
            String codedFileName = java.net.URLEncoder.encode(excelName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="+
                    new String(codedFileName.getBytes("UTF-8"), "UTF-8" )+".xlsx");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/ms-excel");
            OutputStream out = response.getOutputStream();
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            //生成一个表格
            HSSFSheet sheet = wb.createSheet(excelName);
            // 第三步，在sheet中添加表头第0行
            HSSFRow row = sheet.createRow(0);
            row.createCell(1).setCellValue("1111");
            row.createCell(2).setCellValue("1111");
            wb.write(out);
            out.flush();
            out.close();
            wb.close();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}