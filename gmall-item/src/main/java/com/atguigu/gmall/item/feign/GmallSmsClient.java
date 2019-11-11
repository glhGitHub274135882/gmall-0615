package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-11 9:10
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
