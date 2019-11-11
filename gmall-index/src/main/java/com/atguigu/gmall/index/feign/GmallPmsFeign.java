package com.atguigu.gmall.index.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-10 13:42
 */
@FeignClient("pms-service")
public interface GmallPmsFeign extends GmallPmsApi {
}
