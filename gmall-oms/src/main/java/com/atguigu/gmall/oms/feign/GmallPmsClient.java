package com.atguigu.gmall.oms.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author shkstart
 * @create 2019-11-17 15:51
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {

}
