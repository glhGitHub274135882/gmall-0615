package com.atguigu.gmall.pms.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-03 21:02
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
