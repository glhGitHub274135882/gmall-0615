package com.atguigu.gmall.search;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.entity.WareSkuEntity;
import com.atguigu.gmall.pms.entity.BrandEntity;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.entity.SkuInfoEntity;
import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import com.atguigu.gmall.pms.vo.SpuAttributeValueVO;
import com.atguigu.gmall.search.feign.GmallPmsClient;
import com.atguigu.gmall.search.feign.GmallWmsClient;
import com.atguigu.gmall.search.vo.GoodsVO;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class GmallSearchApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private GmallPmsClient gmallPmsClient;

    @Autowired
    private GmallWmsClient gmallWmsClient;

    @Test
    public void importData(){
        Long pageNumber =1l;
        Long pageSize =100l;

        do{
            QueryCondition condition = new QueryCondition();
            condition.setPage(pageNumber);
            condition.setLimit(pageSize);
            Resp<PageVo> pageVoResp = this.gmallPmsClient.list(condition);
            PageVo respData = pageVoResp.getData();
            if(respData == null){
                pageSize = 0l;
                continue;
            }
            List<SpuInfoEntity> spuInfoEntities = (List<SpuInfoEntity>)respData.getList();
            for (SpuInfoEntity spuInfoEntity : spuInfoEntities) {
                Resp<List<SkuInfoEntity>> skuResp = this.gmallPmsClient.querySkuBySpuId(spuInfoEntity.getId());
                List<SkuInfoEntity> skuInfoEntities = skuResp.getData();
                if(CollectionUtils.isEmpty(skuInfoEntities)){
                    continue;
                }
                skuInfoEntities.forEach(skuInfoEntity -> {
                    GoodsVO goodsVO = new GoodsVO();
                    goodsVO.setName(skuInfoEntity.getSkuTitle());
                    goodsVO.setId(skuInfoEntity.getSkuId());
                    goodsVO.setPic(skuInfoEntity.getSkuDefaultImg());
                    goodsVO.setSale(100);
                    goodsVO.setSort(0);

                    Resp<BrandEntity> brandEntityResp = this.gmallPmsClient.queryBrandById(skuInfoEntity.getBrandId());
                    BrandEntity brandEntity = brandEntityResp.getData();
                    if(brandEntity !=null){
                        goodsVO.setBrandId(skuInfoEntity.getBrandId());
                        goodsVO.setBrandName(brandEntity.getName());
                    }
                    Resp<CategoryEntity> categoryEntityResp = this.gmallPmsClient.queryCategoryById(skuInfoEntity.getCatalogId());
                    CategoryEntity categoryEntity = categoryEntityResp.getData();
                    if(categoryEntity !=null){
                        goodsVO.setProductCategoryId(skuInfoEntity.getCatalogId());
                        goodsVO.setProductCategoryName(categoryEntity.getName());
                    }
                    Resp<List<SpuAttributeValueVO>> searchAttrValue = this.gmallPmsClient.querySearchAttrValue(spuInfoEntity.getId());
                    List<SpuAttributeValueVO> spuAttributeValueVOList = searchAttrValue.getData();
                    goodsVO.setAttrValueList(spuAttributeValueVOList);
                    Resp<List<WareSkuEntity>> resp = this.gmallWmsClient.queryWareSkuBySkuId(skuInfoEntity.getSkuId());
                    List<WareSkuEntity> wareSkuEntities = resp.getData();
                    if (wareSkuEntities.stream().anyMatch(t -> t.getStock() > 0)) {
                        goodsVO.setStock(1l);
                    } else {
                        goodsVO.setStock(0l);
                    }
                    Index index = new Index.Builder(goodsVO).index("goods").type("info").id(skuInfoEntity.getSkuId().toString()).build();
                    try {
                        this.jestClient.execute(index);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            pageSize = Long.valueOf(respData.getPageSize());
            pageNumber++;
        }while (pageSize == 100);

    }


    @Test
    void contextLoads() throws IOException {
        Index index = new Index.Builder(new User("liuyan", "123456", 18)).index("user").type("info").id("1").build();
        jestClient.execute(index);
//        Index update =new Index.Builder(new User("gaole","123456",null)).index("user").type("info").id("1").build();
//        DocumentResult result = jestClient.execute(update);
//        System.out.println(result.toString());
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private String password;
    private Integer age;
}

