package com.atguigu.gmall.wms.vo;

import lombok.Data;

/**
 * @author shkstart
 * @create 2019-11-17 12:45
 */
@Data
public class SkuLockVO {
    private Long skuId;
    private Integer count;
    private Boolean lock; // 锁定成功true，锁定失败false
    private Long skuWareId; // 锁定库存的id

    private String orderToken; // 订单号
}
