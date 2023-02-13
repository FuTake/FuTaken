package com.file.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供应商商品管理
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
@Getter
@Setter
//@Accessors(chain = true) easy不能使用该注解
@TableName("supplier_good")
@ApiModel(value = "SupplierGood对象", description = "供应商商品管理")
public class SupplierGood implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("雪花id")
    @ExcelProperty(index = 0)
    private String snowId;

    @ApiModelProperty("商品编号")
    @ExcelProperty(index = 1)
    private String goodsNumber;

    @ApiModelProperty("商品id")
    @ExcelProperty(index = 2)
    private Long goodId;

    @ApiModelProperty("供应商名称")
    @ExcelProperty(index = 3)
    private String supplierName;

    @ApiModelProperty("商品别名")
    @ExcelProperty(index = 4)
    private String goodAlias;

    @ApiModelProperty("商品一级分类id")
    @ExcelProperty(index = 5)
    private Integer oneCategoryId;

    @ApiModelProperty("商品一级分类名称")
    @ExcelProperty(index = 6)
    private String oneCategoryClass;

    @ApiModelProperty("商品二级分类id")
    @ExcelProperty(index = 7)
    private Integer twoCategoryId;

    @ApiModelProperty("商品二级分类名称")
    @ExcelProperty(index = 8)
    private String twoCategoryClass;

    @ApiModelProperty("二级分类pid")
    @ExcelProperty(index = 9)
    private Long twoClasspid;

    @ApiModelProperty("商品名称")
    @ExcelProperty(index = 10)
    private String goodName;

    @ApiModelProperty("商品品牌")
    @ExcelProperty(index = 11)
    private String goodBrand;

    @ApiModelProperty("商品产地")
    @ExcelProperty(index = 12)
    private String goodPlace;

    @ApiModelProperty("商品溯源信息")
    @ExcelProperty(index = 13)
    private String goodRoot;

    @ApiModelProperty("商品溯源说明")
    @ExcelProperty(index = 14)
    private String goodRootRemark;

    @ApiModelProperty("是否上架 0下架 1上架")
    @ExcelProperty(index = 15)
    private Integer isNotShelves;

    @ApiModelProperty("创建人id")
    @ExcelProperty(index = 16)
    private String createId;

    @ApiModelProperty("供应商id")
    @ExcelProperty(index = 17)
    private String supplierId;

    @ApiModelProperty("创建时间")
    @ExcelProperty(index = 18)
    private Date createTime;

    @ApiModelProperty("修改人id")
    @ExcelProperty(index = 19)
    private Long updateId;

    @ApiModelProperty("修改时间")
    @ExcelProperty(index = 20)
    private Date updateTime;

    @ApiModelProperty("状态")
    @ExcelProperty(index = 21)
    private Integer status;

    @ApiModelProperty("审批状态（0， 未审核 1 审核通过 1 审核驳回）")
    @ExcelProperty(index = 22)
    private Integer approvalState;

    @ApiModelProperty("供应商状态（0 上架 1 下架）")
    @ExcelProperty(index = 23)
    private Integer supplierState;

    @ApiModelProperty("系统端上架状态 （0 上架 1 下架）")
    @ExcelProperty(index = 24)
    private Integer systemState;

    @ApiModelProperty("说明")
    @ExcelProperty(index = 25)
    private String remark;

    @ApiModelProperty("选择好分类和名称，自动显示编码")
    @ExcelProperty(index = 26)
    private String goodCode;

    @ApiModelProperty("单位")
    @ExcelProperty(index = 27)
    private String unit;

    @ApiModelProperty("商品价格")
    @ExcelProperty(index = 28)
    private BigDecimal goodPrice;

    @ApiModelProperty("商品规格描述")
    @ExcelProperty(index = 29)
    private String specificationsRemark;

    @ApiModelProperty("损耗率")
    @ExcelProperty(index = 30)
    private Integer loss;

    @ApiModelProperty("商品标签id")
    @ExcelProperty(index = 31)
    private Long goodLabelId;

    @ApiModelProperty("标签名称")
    @ExcelProperty(index = 32)
    private String goodLabel;

    @ApiModelProperty("税收分类编码")
    @ExcelProperty(index = 33)
    private String rateCode;

    @ApiModelProperty("税率")
    @ExcelProperty(index = 34)
    private String rate;

    @ApiModelProperty("商品主图")
    @ExcelProperty(index = 35)
    private String goodMainImage;

    @ApiModelProperty("0 未删除 1删除")
    @ExcelProperty(index = 36)
    private Integer deleted;

    @ApiModelProperty("创建人")
    @ExcelProperty(index = 37)
    private String createName;

    @ApiModelProperty("商品规格")
    @ExcelProperty(index = 38)
    private String specifications;

    @ApiModelProperty("农民信息图片")
    @ExcelProperty(index = 39)
    private String farmingImages;

    @ApiModelProperty("起订量")
    @ExcelProperty(index = 40)
    private Integer startNumber;


}
