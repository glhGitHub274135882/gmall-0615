package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-06 11:47
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
