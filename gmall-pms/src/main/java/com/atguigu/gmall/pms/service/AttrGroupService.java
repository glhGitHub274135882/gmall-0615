package com.atguigu.gmall.pms.service;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.vo.AttrGroupVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 属性分组
 *
 * @author gaolihong
 * @email 274135882@qq.com
 * @date 2019-10-29 14:12:33
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo queryByCidPage(Long cid, QueryCondition condition);

    AttrGroupVO queryById(Long gid);

    List<AttrGroupVO> queryGroupWithAttrsByCid(Long cid);
}

