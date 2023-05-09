package com.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 供应商商品管理-copy
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("supplier_good_copy")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@ApiModel(value = "SupplierGoodCopy对象", description = "供应商商品管理-copy")
public class SupplierGoodCopy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("雪花id")
    private String snowId;

    @ApiModelProperty("商品编号")
    private String goodsNumber;

    @ApiModelProperty("商品id")
    private Long goodId;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("商品别名")
    private String goodAlias;

    @ApiModelProperty("商品一级分类id")
    private Integer oneCategoryId;

    @ApiModelProperty("商品一级分类名称")
    private String oneCategoryClass;

    @ApiModelProperty("商品二级分类id")
    private Integer twoCategoryId;

    @ApiModelProperty("商品二级分类名称")
    private String twoCategoryClass;

    @ApiModelProperty("二级分类pid")
    private Long twoClasspid;

    @ApiModelProperty("商品名称")
    private String goodName;

    @ApiModelProperty("商品品牌")
    private String goodBrand;

    @ApiModelProperty("商品产地")
    private String goodPlace;

    @ApiModelProperty("商品溯源信息")
    private String goodRoot;

    @ApiModelProperty("商品溯源说明")
    private String goodRootRemark;

    @ApiModelProperty("是否上架 0下架 1上架")
    private Integer isNotShelves;

    @ApiModelProperty("创建人id")
    private String createId;

    @ApiModelProperty("供应商id")
    private String supplierId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人id")
    private Long updateId;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("审批状态（0， 未审核 1 审核通过 1 审核驳回）")
    private Integer approvalState;

    @ApiModelProperty("供应商状态（0 上架 1 下架）")
    private Integer supplierState;

    @ApiModelProperty("系统端上架状态 （0 上架 1 下架）")
    private Integer systemState;

    @ApiModelProperty("说明")
    private String remark;

    @ApiModelProperty("选择好分类和名称，自动显示编码")
    private String goodCode;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("商品价格")
    private BigDecimal goodPrice;

    @ApiModelProperty("商品规格描述")
    private String specificationsRemark;

    @ApiModelProperty("损耗率")
    private Integer loss;

    @ApiModelProperty("商品标签id")
    private Long goodLabelId;

    @ApiModelProperty("标签名称")
    private String goodLabel;

    @ApiModelProperty("税收分类编码")
    private String rateCode;

    @ApiModelProperty("税率")
    private String rate;

    @ApiModelProperty("商品主图")
    private String goodMainImage;

    @ApiModelProperty("0 未删除 1删除")
    private Integer deleted;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("商品规格")
    private String specifications;

    @ApiModelProperty("农民信息图片")
    private String farmingImages;

    @ApiModelProperty("起订量")
    private Integer startNumber;

    public SupplierGoodCopy() {

    }

    public SupplierGoodCopy(
            String snowId, String goodsNumber, Long goodId, String supplierName, String goodAlias,
            Integer oneCategoryId, String oneCategoryClass, Integer twoCategoryId, String twoCategoryClass,Long twoClasspid, 
            String goodName, String goodBrand, String goodPlace, String goodRoot,String goodRootRemark, 
            Integer isNotShelves, String createId, String supplierId,Date createTime, Long updateId, 
            Date updateTime, Integer status,Integer approvalState, Integer supplierState, Integer systemState,
            String remark,String goodCode, String unit, BigDecimal goodPrice, String specificationsRemark,
            Integer loss, Long goodLabelId, String goodLabel, String rateCode, String rate,
            String goodMainImage, Integer deleted, String createName, String specifications, String farmingImages,
            Integer startNumber) {
        this.snowId = snowId;
        this.goodsNumber = goodsNumber;
        this.goodId = goodId;
        this.supplierName = supplierName;
        this.goodAlias = goodAlias;
        this.oneCategoryId = oneCategoryId;
        this.oneCategoryClass = oneCategoryClass;
        this.twoCategoryId = twoCategoryId;
        this.twoCategoryClass = twoCategoryClass;
        this.twoClasspid = twoClasspid;
        this.goodName = goodName;
        this.goodBrand = goodBrand;
        this.goodPlace = goodPlace;
        this.goodRoot = goodRoot;
        this.goodRootRemark = goodRootRemark;
        this.isNotShelves = isNotShelves;
        this.createId = createId;
        this.supplierId = supplierId;
        this.createTime = createTime;
        this.updateId = updateId;
        this.updateTime = updateTime;
        this.status = status;
        this.approvalState = approvalState;
        this.supplierState = supplierState;
        this.systemState = systemState;
        this.remark = remark;
        this.goodCode = goodCode;
        this.unit = unit;
        this.goodPrice = goodPrice;
        this.specificationsRemark = specificationsRemark;
        this.loss = loss;
        this.goodLabelId = goodLabelId;
        this.goodLabel = goodLabel;
        this.rateCode = rateCode;
        this.rate = rate;
        this.goodMainImage = goodMainImage;
        this.deleted = deleted;
        this.createName = createName;
        this.specifications = specifications;
        this.farmingImages = farmingImages;
        this.startNumber = startNumber;
    }
}
