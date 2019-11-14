package com.atguigu.gmall.auth.feign;

import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-12 23:35
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
