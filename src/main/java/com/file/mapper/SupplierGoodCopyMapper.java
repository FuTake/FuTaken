package com.file.mapper;

import com.file.entity.SupplierGoodCopy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 供应商商品管理-copy Mapper 接口
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
@Mapper
@Repository
public interface SupplierGoodCopyMapper extends BaseMapper<SupplierGoodCopy> {

}
