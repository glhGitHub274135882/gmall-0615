package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * spu属性值
 *
 * @author gaolihong
 * @email 274135882@qq.com
 * @date 2019-10-29 14:12:33
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {
    List<ProductAttrValueEntity> querySearchAttrValue(Long spuId);
}
