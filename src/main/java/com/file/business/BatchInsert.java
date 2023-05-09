package com.file.business;

import com.alibaba.excel.util.ListUtils;
import com.file.entity.SupplierGoodCopy;
import com.file.mapper.SupplierGoodCopyMapper;
import com.file.service.SupplierGoodCopyService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BatchInsert
 * @Description 批量新增
 * @Author andy
 * @Date 2022/10/28 9:58
 * @Version 1.0
 */
@Data
@Slf4j
public class BatchInsert implements Runnable{

    private XSSFSheet sheet;
    private int begin;
    private int lengths;
    private int lastRowNum;
    private SupplierGoodCopyService service;

    public BatchInsert(XSSFSheet sheet, int begin, int lengths, int lastRowNum, SupplierGoodCopyService service) {
        this.sheet = sheet;
        this.begin = begin;
        this.lengths = lengths;
        this.lastRowNum = lastRowNum;
        this.service = service;
    }

    @Override
    public void run() {
        log.info("启动线程处理");
        List<SupplierGoodCopy> list = new ArrayList<>();
        try {
            int i = 0;
            //分行和列读取
            for(int row = begin; row < (begin+lengths) && row<=lastRowNum; row++) {
                String ten = sheet.getRow(row).getCell(9)==null?"0":sheet.getRow(row).getCell(9).getStringCellValue();
                String t19 = sheet.getRow(row).getCell(19)==null?"0":sheet.getRow(row).getCell(19).getStringCellValue();
                String t28 = sheet.getRow(row).getCell(28)==null?"0":sheet.getRow(row).getCell(28).getNumericCellValue()+"";
                String t30 = sheet.getRow(row).getCell(30)==null?"0":sheet.getRow(row).getCell(30).getNumericCellValue()+"";
                String t12 = sheet.getRow(row).getCell(12)==null?"0":sheet.getRow(row).getCell(12).getNumericCellValue()+"";
                String t2 = sheet.getRow(row).getCell(2)==null?"0":sheet.getRow(row).getCell(2).getStringCellValue();
                String t31 = sheet.getRow(row).getCell(31)==null?"0":sheet.getRow(row).getCell(31).getNumericCellValue()+"";
                String t5 = sheet.getRow(row).getCell(5)==null?"0":sheet.getRow(row).getCell(5).getNumericCellValue()+"";

                try{Long.valueOf(t31);}catch (Exception e){t31 = "0";}
                try{Long.valueOf(t30);}catch (Exception e){t30 = "0";}
                try{Long.valueOf(t5);}catch (Exception e){t5 = "0";}

                SupplierGoodCopy area = new SupplierGoodCopy(
                        sheet.getRow(row).getCell(0)==null?"":sheet.getRow(row).getCell(0).getStringCellValue(),
                        sheet.getRow(row).getCell(1)==null?"":sheet.getRow(row).getCell(1).getStringCellValue(),
                        Long.valueOf(t2),
                        sheet.getRow(row).getCell(3)==null?"":sheet.getRow(row).getCell(3).getStringCellValue(),
                        sheet.getRow(row).getCell(4)==null?"":sheet.getRow(row).getCell(4).getStringCellValue(),
                        Integer.valueOf(t5),
                        sheet.getRow(row).getCell(6)==null?"":sheet.getRow(row).getCell(6).getStringCellValue(),
                        Integer.valueOf((int) sheet.getRow(row).getCell(7).getNumericCellValue()),
                        sheet.getRow(row).getCell(8)==null?"":sheet.getRow(row).getCell(8).getStringCellValue(),
                        Long.valueOf(ten),
                        sheet.getRow(row).getCell(10)==null?"":sheet.getRow(row).getCell(10).getStringCellValue(),
                        sheet.getRow(row).getCell(11)==null?"":sheet.getRow(row).getCell(11).getStringCellValue(),
                        t12,
                        sheet.getRow(row).getCell(13)==null?"":sheet.getRow(row).getCell(13).getStringCellValue(),
                        sheet.getRow(row).getCell(14)==null?"":sheet.getRow(row).getCell(14).getStringCellValue(),
                        Integer.valueOf((int) sheet.getRow(row).getCell(15).getNumericCellValue()),
                        sheet.getRow(row).getCell(16)==null?"":sheet.getRow(row).getCell(16).getStringCellValue(),
                        sheet.getRow(row).getCell(17)==null?"":sheet.getRow(row).getCell(17).getStringCellValue(),
                        sheet.getRow(row).getCell(18).getDateCellValue(),
                        Long.valueOf(t19),
                        sheet.getRow(row).getCell(20)==null?new Date():sheet.getRow(row).getCell(20).getDateCellValue(),
                        Integer.valueOf((int) sheet.getRow(row).getCell(21).getNumericCellValue()),
                        Integer.valueOf((int) sheet.getRow(row).getCell(22).getNumericCellValue()),
                        Integer.valueOf((int) sheet.getRow(row).getCell(23).getNumericCellValue()),
                        Integer.valueOf((int) sheet.getRow(row).getCell(24).getNumericCellValue()),
                        sheet.getRow(row).getCell(25)==null?"":sheet.getRow(row).getCell(25).getStringCellValue(),
                        sheet.getRow(row).getCell(26)==null?"":sheet.getRow(row).getCell(26).getStringCellValue(),
                        sheet.getRow(row).getCell(27)==null?"":sheet.getRow(row).getCell(27).getStringCellValue(),
                        new BigDecimal(t28),
                        sheet.getRow(row).getCell(29)==null?"":sheet.getRow(row).getCell(29).getStringCellValue(),
                        Integer.valueOf(t30),
                        Long.valueOf(t31),
                        sheet.getRow(row).getCell(32)==null?"":sheet.getRow(row).getCell(32).getStringCellValue(),
                        sheet.getRow(row).getCell(33)==null?"":sheet.getRow(row).getCell(33).getStringCellValue(),
                        sheet.getRow(row).getCell(34)==null?"":sheet.getRow(row).getCell(34).getStringCellValue(),
                        sheet.getRow(row).getCell(35)==null?"":sheet.getRow(row).getCell(35).getStringCellValue(),
                        Integer.valueOf((int) sheet.getRow(row).getCell(36).getNumericCellValue()),
                        sheet.getRow(row).getCell(37)==null?"":sheet.getRow(row).getCell(37).getStringCellValue(),
                        sheet.getRow(row).getCell(38)==null?"":sheet.getRow(row).getCell(38).getStringCellValue(),
                        sheet.getRow(row).getCell(39)==null?"":sheet.getRow(row).getCell(39).getStringCellValue(),
                        Integer.valueOf((int) sheet.getRow(row).getCell(40).getNumericCellValue())
                );
                list.add(area);
                i++;
                if(i>=1000){
                    service.saveBatch(list);
                    list = ListUtils.newArrayListWithExpectedSize(i);
                    i=0;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        service.saveBatch(list);
    }
}
