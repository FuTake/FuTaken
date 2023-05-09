package com.file.service;

import com.file.entity.SupplierGoodCopy;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 供应商商品管理-copy 服务类
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
public interface SupplierGoodCopyService extends IService<SupplierGoodCopy> {

    boolean poiFileRead(MultipartFile file);

    boolean easyFileRead(MultipartFile file) throws IOException;
}