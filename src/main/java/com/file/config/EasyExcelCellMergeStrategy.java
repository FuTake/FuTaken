//package com.file.config;
//import com.alibaba.excel.metadata.CellData;
//import com.alibaba.excel.metadata.Head;
//import com.alibaba.excel.write.handler.CellWriteHandler;
//import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
//import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
//import lombok.Data;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import java.util.List;
//
//
///**
// * easyExcel 2.2.1 版本 和data-operation-poseiddon一致
// */
//@Data
//public class EasyExcelCellMergeStrategy implements CellWriteHandler {
//    /**
//     * 合并字段的下标，如第一到五列new int[]{0,1,2,3,4}
//     */
//    private int[] mergeColumnIndex;
//    /**
//     * 从第几行开始合并，如果表头占两行，这个数字就是2
//     */
//    private int mergeRowIndex;
//
//    public EasyExcelCellMergeStrategy() {
//    }
//
//    public EasyExcelCellMergeStrategy(int mergeRowIndex, int[] mergeColumnIndex) {
//        this.mergeRowIndex = mergeRowIndex;
//        this.mergeColumnIndex = mergeColumnIndex;
//    }
//
//    @Override
//    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
//                                 Head head, Integer integer, Integer integer1, Boolean aBoolean) {
//
//    }
//
//    @Override
//    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
//                                Head head, Integer integer, Boolean aBoolean) {
//
//    }
//
//    @Override
//    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//
//    }
//
//    @Override
//    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
////当前行
//        int curRowIndex = cell.getRowIndex();
//        //当前列
//        int curColIndex = cell.getColumnIndex();
//
//        //固定合并第三行的0，1单元格
//        if(curRowIndex == 2 && curColIndex == 1){
//            // 拿到当前表
//            Sheet sheet = writeSheetHolder.getSheet();
//            // 新建合并单元
//            CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex, curRowIndex, curColIndex - 1,
//                    curColIndex);
//            sheet.addMergedRegion(cellRangeAddress);
//        }
//
//        if (curRowIndex > mergeRowIndex) {
//            for (int i = 0; i < mergeColumnIndex.length; i++) {
//                if (curColIndex == mergeColumnIndex[i]) {
//                    mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
//                    break;
//                }
//            }
//        }
//    }
//
//    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
//        //获取当前行的当前列的数据和上一行的当前列列数据，通过上一行数据是否相同进行合并
//        Object curData = cell.getCellType() == CellType.STRING ? cell.getStringCellValue() :
//                cell.getNumericCellValue();
//        Cell preCell = cell.getSheet().getRow(curRowIndex - 1).getCell(curColIndex);
//        Object preData = preCell.getCellType() == CellType.STRING ? preCell.getStringCellValue() :
//                preCell.getNumericCellValue();
//        // 比较当前行的第一列的单元格与上一行是否相同，相同合并当前单元格与上一行
//        //
//        if (curData.equals(preData)) {
//            Sheet sheet = writeSheetHolder.getSheet();
//            List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
//            boolean isMerged = false;
//            for (int i = 0; i < mergeRegions.size() && !isMerged; i++) {
//                CellRangeAddress cellRangeAddr = mergeRegions.get(i);
//                // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
//                if (cellRangeAddr.isInRange(curRowIndex - 1, curColIndex)) {
//                    sheet.removeMergedRegion(i);
//                    cellRangeAddr.setLastRow(curRowIndex);
//                    sheet.addMergedRegion(cellRangeAddr);
//                    isMerged = true;
//                }
//            }
//            // 若上一个单元格未被合并，则新增合并单元
//            if (!isMerged) {
//                CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex,
//                        curColIndex);
//                sheet.addMergedRegion(cellRangeAddress);
//            }
//        }
//    }
//}
