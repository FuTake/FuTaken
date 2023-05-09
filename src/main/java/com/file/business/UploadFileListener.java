package com.file.business;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.file.entity.SupplierGoodCopy;
import com.file.service.SupplierGoodCopyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UploadFileListener
 * @Description easyexcel读取数据
 * @Author andy
 * @Date 2022/10/28 11:04
 * @Version 1.0
 */
@Slf4j
public class UploadFileListener implements ReadListener<SupplierGoodCopy> {
    /**
     * 500条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;
    /**
     * 缓存的数据
     */
    private List<SupplierGoodCopy> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 一个service。当然如果不用存储这个对象没用。
     */
    private SupplierGoodCopyService service;

    /**
     * 每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param service
     */
    public UploadFileListener(SupplierGoodCopyService service) {
        this.service = service;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(SupplierGoodCopy data, AnalysisContext context) {
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    @Transactional
    public void saveData() {
        service.saveBatch(cachedDataList);
        log.info("写入一次: " + cachedDataList.size());
    }
}
