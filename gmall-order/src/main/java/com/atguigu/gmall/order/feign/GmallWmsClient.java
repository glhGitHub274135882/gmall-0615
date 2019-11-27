package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-15 20:40
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
