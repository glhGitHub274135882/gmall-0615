package com.atguigu.gmall.cart.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-14 15:07
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
