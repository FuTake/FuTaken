package com.file.service.impl;

import com.alibaba.excel.EasyExcel;
import com.file.business.BatchInsert;
import com.file.config.ThreadPoolConfig;
import com.file.entity.SupplierGoodCopy;
import com.file.mapper.SupplierGoodCopyMapper;
import com.file.service.SupplierGoodCopyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * <p>
 * 供应商商品管理-copy 服务实现类
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
@Service
@Slf4j
public class SupplierGoodCopyServiceImp extends ServiceImpl<SupplierGoodCopyMapper, SupplierGoodCopy> implements SupplierGoodCopyService {

    @Autowired
    Executor myThreadPoolExecutor;

    /**
     * @Author andy
     * @Description 文件读取 poi方式，并写入数据库
     * @Date 11:37 2022/10/27
     * @Param [file]
     * @return boolean
     **/
    @Override
    public boolean poiFileRead(MultipartFile file){
//        readGoodOutOf(file);  //单条新增  本地数据库不存在网络问题，所以与批量操作效率差距不明显
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();//所有行数
            int row = 0;
//            while (row<=lastRowNum){
//                //循环读取,每次处理500条
//                saveBatch(readGoodWhile(sheet,row,1000,lastRowNum));
//                row+=1000;
//            }
            //多线程线程池方式操作
            while (row<=lastRowNum){
                //循环读取,每次处理500条
                myThreadPoolExecutor.execute(new BatchInsert(sheet,row,10000,lastRowNum,this));
                row+=10000;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     * @Author andy
     * @Description //读取实体
     * @Date 11:41 2022/10/27
     * @Param [file]
     * @return java.util.List<com.file.entity.SupplierGoodCopy>
     **/
    public boolean readGoodOutOf(MultipartFile file){

        try {
            XSSFWorkbook workbook =  new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            int lastRowNum = sheet.getLastRowNum();
            log.info("最后行号"+lastRowNum);

            //分行和列读取
            for(int row = 0; row < lastRowNum && row<=lastRowNum; row++) {
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
                save(area);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @Author andy
     * @Description //循环读取实体
     * @Date 11:41 2022/10/27
     * @Param [file]
     * @return java.util.List<com.file.entity.SupplierGoodCopy>
     **/
    @Async("myThreadPoolExecutor")
    public List<SupplierGoodCopy> readGoodWhile(XSSFSheet sheet,int begin,int lengths,int lastRowNum){
        List<SupplierGoodCopy> list = new ArrayList<>();
        try {
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
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
