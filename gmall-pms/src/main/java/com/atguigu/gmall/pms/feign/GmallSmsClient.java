package com.atguigu.gmall.pms.feign;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.vo.SaleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author shkstart
 * @create 2019-11-03 21:02
 */
@FeignClient("sms-service")
public interface GmallSmsClient {
    @PostMapping("sms/skubounds/sale")
    public Resp<Object> saveSale(@RequestBody SaleVO saleVO);
}
