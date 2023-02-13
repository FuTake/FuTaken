package com.file.controller;

import com.file.cons.Constants;
import com.file.service.SupplierGoodCopyService;
import com.file.service.SupplierGoodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName UploadController
 * @Description 上传文件
 * @Author andy
 * @Date 2022/10/25 16:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private SupplierGoodCopyService supplierGoodCopyService;
    @Autowired
    private SupplierGoodService easyService;
    /**
     * @Author andy
     * @Description //文件读取 poi方式，并写入数据库
     * @Date 9:38 2022/10/26
     * @Param [file]
     * @return java.lang.String
     **/
    @PostMapping("/poi")
    public String poiFileRead(@RequestParam("file") MultipartFile file) {
        long time = System.currentTimeMillis();
        log.info("开始时间："+ DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        supplierGoodCopyService.poiFileRead(file);
        log.info("时间差："+ (System.currentTimeMillis()-time));
        return "成功";
    }

    /**
     * @Author andy
     * @Description //文件读取 easyExcel，并写入数据库
     * @Date 9:38 2022/10/26
     * @Param [file]
     * @return java.lang.String
     **/
    @PostMapping("/easy")
    public String easyFileRead(@RequestParam("file") MultipartFile file) throws IOException {
        long time = System.currentTimeMillis();
        log.info("开始时间："+ DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        easyService.easyFileRead(file);
        log.info("时间差："+ (System.currentTimeMillis()-time));
        return "成功";
    }

    /**
     * @Author andy
     * @Description //文件上传，简单未封装
     * @Date 9:39 2022/10/26
     * @Param [file]
     * @return java.lang.String
     **/
    @PostMapping("/upload")
    public String FileUpLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String realPath = Constants.UPLOAD_URL;
        String format = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
        File folder = new File(realPath + format);
        String filePath="";
        if (!folder.isDirectory()) {
            folder.mkdirs();
            String oldName = file.getOriginalFilename();
            String newName = UUID.randomUUID() +
                    oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                file.transferTo(new File(folder, newName));
                filePath = request.getScheme() + "://" + request.getServerName() + ":" +
                        request.getServerPort() + "/uploadFile/" + format + newName;

            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败! ";
            }
        }
        return filePath;
    }

}
