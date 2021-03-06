package com.atguigu.gmall.order.service;

import com.atguigu.gmall.oms.entity.OrderEntity;
import com.atguigu.gmall.oms.vo.OrderSubmitVO;
import com.atguigu.gmall.order.entity.vo.OrderConfirmVO;

/**
 * @author shkstart
 * @create 2019-11-15 20:24
 */
public interface OrderService {
    OrderConfirmVO confirm();

    OrderEntity submit(OrderSubmitVO orderSubmitVO);

    void paySucess(String out_trade_no);

    OrderEntity queryOrder();
}
