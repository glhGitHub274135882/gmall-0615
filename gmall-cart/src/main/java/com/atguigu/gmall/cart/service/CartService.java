package com.atguigu.gmall.cart.service;

import com.atguigu.gmall.cart.vo.Cart;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-14 15:14
 */
public interface CartService {
    void addCart(Cart cart);

    List<Cart> queryCarts();

    void updateCart(Cart cart);

    void deleteCart(Long skuId);

    void checkCart(List<Cart> carts);
}
