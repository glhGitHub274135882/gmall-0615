package com.atguigu.gmall.sms.api;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.sms.vo.ItemSaleVO;
import com.atguigu.gmall.sms.vo.SaleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-05 20:09
 */
public interface GmallSmsApi {

    @GetMapping("sms/skubounds/item/sales/{skuId}")
    public Resp<List<ItemSaleVO>> queryItemSaleVOs(@PathVariable("skuId")Long skuId);

    @PostMapping("sms/skubounds/sale")
    public Resp<Object> saveSale(@RequestBody SaleVO saleVO);
}
