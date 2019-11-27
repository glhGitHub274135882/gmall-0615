package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-15 20:36
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
