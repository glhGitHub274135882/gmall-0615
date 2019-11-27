package com.atguigu.gmall.ums.feign;

import com.atguigu.gmall.msm.api.GmallMsmApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-18 16:53
 */
@FeignClient("msm-service")
public interface GmallMsmClient extends GmallMsmApi {
}
