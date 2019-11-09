package com.atguigu.gmall.pms;

import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.gmall.pms.dao.BrandDao;
import com.atguigu.gmall.pms.entity.BrandEntity;
import com.atguigu.gmall.pms.entity.SpuInfoEntity;
import com.atguigu.gmall.pms.service.BrandService;
import com.atguigu.gmall.pms.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GmallPmsApplicationTests {

    @Autowired
    private SpuInfoService spuInfoService;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    BrandService brandService;

    @Autowired
    StringRedisTemplate redisTemplate = new StringRedisTemplate();

    @Test
    void contextLoads() {

        double stringValueLong = redisTemplate.opsForValue().increment("longKey",6);
        System.out.println("通过increment(K key, long delta)方法以增量方式存储long值:" + stringValueLong);
    }

    @Test
    public void Test(){
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setDescript("尚硅谷真好!!");
//        brandEntity.setFirstLetter("S");
//        brandEntity.setLogo("www.baidu.com/log.gif");
//        brandEntity.setName("尚硅谷");
//        brandEntity.setSort(1);
//        brandEntity.setShowStatus(0);
//        this.brandDao.insert(brandEntity);
//        Map<String,Object> map = new HashMap<>();
//        map.put("name ","gaole");
//        this.brandDao.deleteByMap(map);
//        System.out.println(this.brandDao.selectList(new QueryWrapper<BrandEntity>().eq("name", "尚硅谷")));
//        IPage<BrandEntity> page = this.brandDao.selectPage(new Page<BrandEntity>(2,2),new QueryWrapper<BrandEntity>());
//        System.out.println(page.getRecords());
//        System.out.println(page.getPages());
//        System.out.println(page.getTotal());
        QueryCondition queryCondition = new QueryCondition();
        PageVo page = spuInfoService.queryPage(queryCondition);
        System.out.println((List<SpuInfoEntity>) page.getList());
    }

}
