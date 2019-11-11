package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.ProductAttrValueEntity;
import lombok.Data;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-11 8:40
 */
@Data
public class GroupVO {

    private String groupName;

    private List<ProductAttrValueEntity> baseAttrValues;
}