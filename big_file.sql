/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : big_file

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 28/10/2022 13:38:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for supplier_good
-- ----------------------------
DROP TABLE IF EXISTS `supplier_good`;
CREATE TABLE `supplier_good`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `snow_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '雪花id',
  `goods_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `good_id` bigint(100) NULL DEFAULT NULL COMMENT '商品id',
  `supplier_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `good_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品别名',
  `one_category_id` int(32) NULL DEFAULT NULL COMMENT '商品一级分类id',
  `one_category_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品一级分类名称',
  `two_category_id` int(32) NULL DEFAULT NULL COMMENT '商品二级分类id',
  `two_category_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品二级分类名称',
  `two_classpid` bigint(32) NULL DEFAULT NULL COMMENT '二级分类pid',
  `good_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `good_brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品品牌',
  `good_place` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品产地',
  `good_root` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品溯源信息',
  `good_root_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品溯源说明',
  `is_not_shelves` int(5) NULL DEFAULT 0 COMMENT '是否上架 0下架 1上架',
  `create_id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `supplier_id` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(100) NULL DEFAULT NULL COMMENT '修改人id',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(3) NULL DEFAULT 1 COMMENT '状态',
  `approval_state` int(10) NULL DEFAULT 0 COMMENT '审批状态（0， 未审核 1 审核通过 1 审核驳回）',
  `supplier_state` int(10) NULL DEFAULT 1 COMMENT '供应商状态（0 上架 1 下架）',
  `system_state` int(10) NULL DEFAULT 1 COMMENT '系统端上架状态 （0 上架 1 下架）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `good_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选择好分类和名称，自动显示编码',
  `unit` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `good_price` decimal(50, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `specifications_remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品规格描述',
  `loss` int(25) NULL DEFAULT NULL COMMENT '损耗率',
  `good_label_id` bigint(100) NULL DEFAULT NULL COMMENT '商品标签id',
  `good_label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `rate_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '税收分类编码',
  `rate` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '税率',
  `good_main_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品主图',
  `deleted` int(5) NULL DEFAULT 0 COMMENT '0 未删除 1删除',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `specifications` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `farming_images` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '农民信息图片',
  `start_number` int(11) NULL DEFAULT 0 COMMENT '起订量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商商品管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for supplier_good_copy
-- ----------------------------
DROP TABLE IF EXISTS `supplier_good_copy`;
CREATE TABLE `supplier_good_copy`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `snow_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '雪花id',
  `goods_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `good_id` bigint(100) NULL DEFAULT NULL COMMENT '商品id',
  `supplier_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `good_alias` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品别名',
  `one_category_id` int(32) NULL DEFAULT NULL COMMENT '商品一级分类id',
  `one_category_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品一级分类名称',
  `two_category_id` int(32) NULL DEFAULT NULL COMMENT '商品二级分类id',
  `two_category_class` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品二级分类名称',
  `two_classpid` bigint(32) NULL DEFAULT NULL COMMENT '二级分类pid',
  `good_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `good_brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品品牌',
  `good_place` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品产地',
  `good_root` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品溯源信息',
  `good_root_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品溯源说明',
  `is_not_shelves` int(5) NULL DEFAULT 0 COMMENT '是否上架 0下架 1上架',
  `create_id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `supplier_id` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` bigint(100) NULL DEFAULT NULL COMMENT '修改人id',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `status` int(3) NULL DEFAULT 1 COMMENT '状态',
  `approval_state` int(10) NULL DEFAULT 0 COMMENT '审批状态（0， 未审核 1 审核通过 1 审核驳回）',
  `supplier_state` int(10) NULL DEFAULT 1 COMMENT '供应商状态（0 上架 1 下架）',
  `system_state` int(10) NULL DEFAULT 1 COMMENT '系统端上架状态 （0 上架 1 下架）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '说明',
  `good_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选择好分类和名称，自动显示编码',
  `unit` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `good_price` decimal(50, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `specifications_remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品规格描述',
  `loss` int(25) NULL DEFAULT NULL COMMENT '损耗率',
  `good_label_id` bigint(100) NULL DEFAULT NULL COMMENT '商品标签id',
  `good_label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `rate_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '税收分类编码',
  `rate` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '税率',
  `good_main_image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品主图',
  `deleted` int(5) NULL DEFAULT 0 COMMENT '0 未删除 1删除',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `specifications` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `farming_images` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '农民信息图片',
  `start_number` int(11) NULL DEFAULT 0 COMMENT '起订量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商商品管理-copy' ROW_FORMAT = DYNAMIC;

