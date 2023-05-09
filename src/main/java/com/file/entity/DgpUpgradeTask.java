//package com.file.entity;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.annotation.ExcelProperty;
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.read.listener.ReadListener;
//import com.alibaba.excel.util.ListUtils;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.file.business.UploadFileListener;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * <p>
// *
// * </p>
// *
// * @author zhg
// * @since 2023-04-10
// */
//@Getter
//@Setter
//@Accessors(chain = true)
//@TableName("dgp_upgrade_task")
//public class DgpUpgradeTask implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @TableId(value = "id", type = IdType.AUTO)
//    private Integer id;
//
//    /**
//     * 任务编号
//     */
//    @ExcelProperty(index = 2)
//    private String taskNum;
//
//    /**
//     * 任务名称
//     */
//    @ExcelProperty(index = 3)
//    private String taskName;
//
//    /**
//     * 任务内容
//     */
//    @ExcelProperty(index = 4)
//    private String upgradeTask;
//
//    /**
//     * 提升方式
//     */
//    @ExcelProperty(index = 7)
//    private String upgradeType;
//
//    /**
//     * 制度层级
//     */
//    @ExcelProperty(index = 5)
//    private String hierarchy;
//
//    /**
//     * 能力项
//     */
//    @ExcelProperty(index = 13)
//    private String competence;
//
//    /**
//     * 收支明细
//     */
//    @ExcelProperty(index = 6)
//    private String statement;
//
//    /**
//     * 牵头部门
//     */
//    @ExcelProperty(index = 8)
//    private String headDepart;
//
//    /**
//     * 牵头人
//     */
//    @ExcelProperty(index = 9)
//    private String headPerson;
//
//    /**
//     * 实施人
//     */
//    @ExcelProperty(index = 10)
//    private String implementPerson;
//
//    /**
//     * 咨询人
//     */
//    @ExcelProperty(index = 11)
//    private String consultPerson;
//
//    /**
//     * 配合部门
//     */
//    @ExcelProperty(index = 12)
//    private String coorpDepart;
//
//    /**
//     * 完成时间
//     */
//    @ExcelProperty(index = 14)
//    private String completeTime;
//
//    /**
//     * 完成状态
//     */
//    @ExcelProperty(index = 15)
//    private String status;
//
//    /**
//     * 数据类型
//     */
//    private Integer catalogue;
//
//    /**
//     * 目录
//     */
//    @ExcelProperty(index = 1)
//    private String catalogueString;
//
//    public static void main(String[] args) {
//        File file = new File("C:\\Users\\a1557\\Desktop\\work\\提升任务合并版文档.xlsx");
////        EasyExcel.read(file, SupplierGoodCopy.class, new UpgradeTaskListener(this)).sheet().doRead();
//    }
//
//}
//@Slf4j
//class UpgradeTaskListener implements ReadListener<DgpUpgradeTask>{
//
//    private static List<SupplierGoodCopy> cachedDataList = new ArrayList<>();
//    private static int BATCH_COUNT = 500;
//
//    @Override
//    public void invoke(DgpUpgradeTask dgpUpgradeTask, AnalysisContext analysisContext) {
//
//    }
//
//
//    /**
//     * 这个每一条数据解析都会来调用
//     *
//     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
//     * @param context
//     */
//    public void invoke(SupplierGoodCopy data, AnalysisContext context) {
//        cachedDataList.add(data);
//        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (cachedDataList.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//        }
//    }
//
//    /**
//     * 所有数据解析完成了 都会来调用
//     *
//     * @param context
//     */
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        saveData();
//        log.info("所有数据解析完成！");
//    }
//
//    /**
//     * 加上存储数据库
//     */
//    @Transactional
//    public void saveData() {
////        service.saveBatch(cachedDataList);
//        log.info("写入一次: " + cachedDataList.size());
//    }
//}
