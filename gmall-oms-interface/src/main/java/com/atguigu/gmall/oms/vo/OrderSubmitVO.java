package com.atguigu.gmall.oms.vo;

import com.atguigu.gmall.ums.entity.MemberReceiveAddressEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-17 12:21
 */
@Data
public class OrderSubmitVO {

   /* //提交上次订单确认页给你的令牌；
    private String orderToken;

    private BigDecimal totalPrice; // 校验总价格时，拿计算价格和这个价格比较

    private Integer payType;//0-在线支付  1-货到付款

    private String delivery_company; // 配送方式

    private List<OrderItemVO> orderItems; // 订单清单

    // 地址信息，不需要id及memberId
    private String name;
    private String phone;
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;
    private String areacode;
    private Integer defaultStatus;
*/
    // TODO：发票相关信息略

    // TODO：营销信息等

        private Long userId;
        private String userName;

        private MemberReceiveAddressEntity address; // 收货地址

        private Integer payType; // 支付方式

        private String deliveryCompany;// 配送公司（配送方式）

        private List<OrderItemVO> orderItemVOS; // 订单详情

        private Integer useIntegration; // 下单时使用的积分

        private BigDecimal totalPrice; // 总价，用于验价

        private String orderToken; // 防重，订单编号

}
