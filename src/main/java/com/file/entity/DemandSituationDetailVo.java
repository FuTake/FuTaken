package com.file.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DemandSituationDetailVo {

    /**
     * 序号
     */
    @ExcelProperty("序号")
    private Integer ord;

    /**
     * 表id，唯一主键,主键自增
     */
    @ExcelIgnore
    private Long id;

    /**
     * 需求编码
     */
    @ColumnWidth(30)
    @ExcelProperty("需求编码")
    private String demandCode;

    /**
     * 需求名称
     */
    @ColumnWidth(100)
    @ExcelProperty("需求名称")
    private String demandName;

    /**
     * 需求阶段
     */
    @ColumnWidth(10)
    @ExcelProperty("需求阶段")
    private String demandStage;


    /**
     * 需求接收时间
     */
    @ColumnWidth(30)
    @ExcelProperty("需求接收时间")
    private String demandReceiveTime;

    /**
     * 需求交付时间
     */
    @ColumnWidth(30)
    @ExcelProperty("需求交付时间")
    private String demandDeliveryTime;

    /**
     * 需求来源
     */
    @ColumnWidth(30)
    @ExcelProperty("需求来源")
    private String sourceName;

    /**
     * 需求提出部门id
     */
    @ExcelIgnore
    private Long demandGiveDepartmentId;

    /**
     * 提出部门
     */
    @ColumnWidth(30)
    @ExcelProperty("提出部门")
    private String giveDepartment;

    /**
     * 需求提出人员id
     */
    @ExcelIgnore
    private Long demandGivePersonId;

    /**
     * 需求提出人
     */
    @ColumnWidth(30)
    @ExcelProperty("需求提出人")
    private String givePerson;

    /**
     * 需求接收人员id
     */
    @ExcelIgnore
    private Long demandReceivePersonId;

    /**
     * 需求人员
     */
    @ColumnWidth(30)
    @ExcelProperty("需求人员")
    private String demandPerson;

    /**
     * 支撑项目
     */
    @ColumnWidth(40)
    @ExcelProperty("支撑项目")
    private String supportProject;
    /**
     * 满意度
     */
    @ColumnWidth(30)
    @ExcelProperty("满意度")
    private String satisfactionRate;

}
