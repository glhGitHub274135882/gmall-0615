package com.atguigu.gmall.api;

import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.entity.WareSkuEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-06 11:24
 */
public interface GmallWmsApi {

    //("根据skuId查询库存信息")
    @GetMapping("wms/waresku/{skuId}")
    public Resp<List<WareSkuEntity>> queryWareSkuBySkuId(@PathVariable("skuId")Long skuId);
}
