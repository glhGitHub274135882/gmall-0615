package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import lombok.Data;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-01 20:20
 */
@Data
public class AttrGroupVO extends AttrGroupEntity {

    private List<AttrEntity> attrEntities;

    private List<AttrAttrgroupRelationEntity> relations;
}
