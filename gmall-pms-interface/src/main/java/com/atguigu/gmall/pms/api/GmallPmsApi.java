package com.atguigu.gmall.pms.api;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.entity.BrandEntity;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.entity.SkuInfoEntity;
import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import com.atguigu.gmall.pms.vo.SpuAttributeValueVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-11-06 10:33
 */
public interface GmallPmsApi {

   //("分页查询(排序)")
//    @PostMapping("pms/spuinfo/list")
//    public Resp<List<SpuInfoEntity>> querySpuPage(@RequestBody QueryCondition queryCondition);

    @PostMapping("pms/spuinfo/list")
    public Resp<List<SpuInfoEntity>> querySpuPage(@RequestBody QueryCondition queryCondition);

    //("查询spu下的sku")
    @GetMapping("pms/skuinfo/{spuId}")
    public Resp<List<SkuInfoEntity>> querySkuBySpuId(@PathVariable("spuId")Long spuId);

    //("商品详情查询")
    @GetMapping("pms/brand/info/{brandId}")
    public Resp<BrandEntity> queryBrandById(@PathVariable("brandId") Long brandId);

    //("商品分类详情查询")
    @GetMapping("pms/category/info/{catId}")
    public Resp<CategoryEntity> queryCategoryById(@PathVariable("catId") Long catId);

    //("es查询spu")
    @GetMapping("pms/productattrvalue/{spuId}")
    public Resp<List<SpuAttributeValueVO>> querySearchAttrValue(@PathVariable("spuId") Long spuId);

//    @GetMapping("pms/productattrvalue/{spuId}")
//    public Resp<List<SpuAttributeValueVO>> querySearchAttrValue(@PathVariable("spuId")Long spuId);
}
