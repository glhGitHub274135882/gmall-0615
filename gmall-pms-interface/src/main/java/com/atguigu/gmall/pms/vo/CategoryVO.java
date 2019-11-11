package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import lombok.Data;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-10 14:19
 */

@Data
public class CategoryVO extends CategoryEntity {

    private List<CategoryEntity> subs;
}
