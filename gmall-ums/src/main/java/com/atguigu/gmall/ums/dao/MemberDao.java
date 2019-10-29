package com.atguigu.gmall.ums.dao;

import com.atguigu.gmall.ums.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author gaolihong
 * @email 274135882@qq.com
 * @date 2019-10-29 14:19:39
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
