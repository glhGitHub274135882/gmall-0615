package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-11 8:58
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
