package com.file.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.file.config.CustomMergeStrategy;
import com.file.config.ExcelFillCellMergeOneStrategy;
import com.file.config.ExcelFillCellMergePrevCol;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomExcelController {

    /**
     * easyExcel动态表头设置
     */
    @Test
    public void test(){
        /**
         *     //获取自定义表头:1.表头总共占用几行，就要在定义的每个list中添加几条数据 2.表头总共有几列就
         *     //要定义几个list
         */
        List<List<String>> heads = Lists.newArrayList();
        // A1,A2都是“分类”所以会合并
        heads.add(Lists.newArrayList("分类", "分类"));
        heads.add(Lists.newArrayList("提出省分", "提出省分"));
        heads.add(Lists.newArrayList("总需求量","总需求量"));
        heads.add(Lists.newArrayList("已完成需求量", "已完成需求量"));
        heads.add(Lists.newArrayList("已完成需求占比", "已完成需求占比"));
        heads.add(Lists.newArrayList("在途需求量", "合计"));
        heads.add(Lists.newArrayList("在途需求量", "评估中"));
        heads.add(Lists.newArrayList("在途需求量", "确认中"));
        heads.add(Lists.newArrayList("在途需求量", "开发中"));
        heads.add(Lists.newArrayList("在途需求量", "测试中"));
        heads.add(Lists.newArrayList("在途需求量", "发布中"));
        heads.add(Lists.newArrayList("在途需求量占比", "在途需求量占比"));
        heads.add(Lists.newArrayList("评估工时(天)", "评估工时(天)"));
        heads.add(Lists.newArrayList("交付工时(天)", "交付工时(天)"));
        heads.add(Lists.newArrayList("平均评估工时(天)", "平均评估工时(天)"));
        heads.add(Lists.newArrayList("平均交付工时(天)", "平均交付工时(天)"));
        heads.add(Lists.newArrayList("满意度", "满意度"));


        List<List<String>> contents = Lists.newArrayList();
        for (int i = 0; i <= 10; i++) {
            List<String> content = Lists.newArrayList();
            for (int j = 0; j < 17; j++) {
                if(i > 2 && i < 5 && j < 2){
                    content.add("111");
                }else{
                    content.add("第" + i + "行第" + j + "例内容");
                }
            }
            contents.add(content);
        }
//
//        // 表头样式策略
//        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        // 设置数据格式
////        headWriteCellStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat("m/d/yy h:mm"));
//        // 是否换行
//        headWriteCellStyle.setWrapped(false);
//        // 水平对齐方式
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        // 垂直对齐方式
//        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        // 前景色
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//        // 背景色
//        headWriteCellStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
//        // 设置为1时，单元格将被前景色填充
//        headWriteCellStyle.setFillPatternType(FillPatternType.NO_FILL);
//        // 控制单元格是否应自动调整大小以适应文本过长时的大小
//        headWriteCellStyle.setShrinkToFit(false);
//        // 单元格边框类型
//        headWriteCellStyle.setBorderBottom(BorderStyle.NONE);
//        headWriteCellStyle.setBorderLeft(BorderStyle.NONE);
//        headWriteCellStyle.setBorderRight(BorderStyle.NONE);
//        headWriteCellStyle.setBorderTop(BorderStyle.NONE);
//        // 单元格边框颜色
//        headWriteCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
//        headWriteCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
//        headWriteCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
//        headWriteCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
//        // 字体策略
//        WriteFont writeFont = new WriteFont();
//        // 是否加粗/黑体
//        writeFont.setBold(false);
//        // 字体颜色
//        writeFont.setColor(Font.COLOR_NORMAL);
//        // 字体名称
//        writeFont.setFontName("宋体");
//        // 字体大小
//        writeFont.setFontHeightInPoints((short) 11);
//        // 是否使用斜体
//        writeFont.setItalic(false);
//        // 是否在文本中使用横线删除
//        writeFont.setStrikeout(false);
//        // 设置要使用的文本下划线的类型
//        writeFont.setUnderline(Font.U_NONE);
//        // 设置要使用的字符集
////        writeFont.setCharset(FontCharset.DEFAULT.getNativeId());
//        headWriteCellStyle.setWriteFont(writeFont);
//
//        // 内容样式策略策略
//        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.GENERAL);
//        contentWriteCellStyle.setBorderBottom(BorderStyle.NONE);
//        contentWriteCellStyle.setBorderLeft(BorderStyle.NONE);
//        contentWriteCellStyle.setBorderRight(BorderStyle.NONE);
//        contentWriteCellStyle.setBorderTop(BorderStyle.NONE);
//        contentWriteCellStyle.setFillPatternType(FillPatternType.NO_FILL);
//        contentWriteCellStyle.setWrapped(false);
        int size = contents.size();
        ExcelFillCellMergePrevCol col = new ExcelFillCellMergePrevCol();
        col.add(3, 0, 2);
        EasyExcel.write("C:\\Users\\a1557\\Desktop\\test.xlsx")
                .sheet("test")
                .head(heads)
//                .registerWriteHandler(new ExcelFillCellMergeStrategy(size, new int[]{3,0,1}, size + 1))
//                .registerWriteHandler(col)
                .registerWriteHandler(new ExcelFillCellMergeOneStrategy(2, new int[]{0}))
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(contents);

        // 通过接口调用，使用流生成文件
        /*String fileName = "导出测试";
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8") + ".xlsx";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("filename", fileName);
        EasyExcel.write(response.getOutputStream()).head(heads)
                .registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle))
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(16)) // 列宽
                .registerConverter(new LocalDateTimeConverter())
                .sheet("销售订单").doWrite(contents);*/
    }

    /**
     * easyExcel合并单元格
     */
    private void excelMerge(){

    }
}
