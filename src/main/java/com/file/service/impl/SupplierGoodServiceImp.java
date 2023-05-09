package com.file.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.file.business.UploadFileListener;
import com.file.entity.SupplierGood;
import com.file.entity.SupplierGoodCopy;
import com.file.mapper.SupplierGoodMapper;
import com.file.service.SupplierGoodCopyService;
import org.springframework.stereotype.Service;
import com.file.service.SupplierGoodService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 供应商商品管理 服务实现类
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
@Service
public class SupplierGoodServiceImp extends ServiceImpl<SupplierGoodMapper, SupplierGood> implements SupplierGoodService {

    @Resource
    private SupplierGoodCopyService service;

    @Override
    public boolean easyFileRead(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SupplierGoodCopy.class, new UploadFileListener(service)).sheet().doRead();
        return true;
    }

    @Override
    public Map<String, Object> getMaps(){
        return this.getMap(new QueryWrapper<SupplierGood>().eq("two_classpid", 1));
    }
}
