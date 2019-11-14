package com.atguigu.gmall.cart.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-14 15:07
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
