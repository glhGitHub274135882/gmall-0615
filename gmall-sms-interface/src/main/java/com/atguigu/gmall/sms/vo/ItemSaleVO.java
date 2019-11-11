package com.atguigu.gmall.sms.vo;

import lombok.Data;

/**
 * @author shkstart
 * @create 2019-11-11 8:32
 */
@Data
public class ItemSaleVO {

    private String type; // 满减 打折 积分

    private String desc; // 优惠信息的具体描述
}
