package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

/**
 * 商品属性
 *
 * @author gaolihong
 * @email 274135882@qq.com
 * @date 2019-10-29 14:12:33
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

}
