package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-06 11:51
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
