package com.atguigu.gmall.index.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.index.annotation.GmallCache;
import com.atguigu.gmall.index.feign.GmallPmsFeign;
import com.atguigu.gmall.index.service.IndexService;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.vo.CategoryVO;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.TIMEOUT;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2019-11-10 13:54
 */

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private GmallPmsFeign gmallPmsFeign;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedissonClient redissonClient;

    private static final String KEY_FREFIX ="index:category";

    @Override
    public List<CategoryEntity> queryLevelCategory() {
        Resp<List<CategoryEntity>> queryCategory = gmallPmsFeign.queryCategory(1, null);
        return queryCategory.getData();
    }

    @Override
    @GmallCache(prefix = KEY_FREFIX,timeout = 30000l,random = 50l)
    public List<CategoryVO> queryCategoryWhithSub(Long pid) {
     /*   String cache = this.redisTemplate.opsForValue().get(KEY_FREFIX + pid);
        if(StringUtils.isNotBlank(cache)){
            return JSON.parseArray(cache,CategoryVO.class);
        }*/
        Resp<List<CategoryVO>> listResp = this.gmallPmsFeign.queryCategoryWhitSub(pid);
        List<CategoryVO> categoryVOS = listResp.getData();
//        this.redisTemplate.opsForValue().set(KEY_FREFIX+pid,JSON.toJSONString(categoryVOS),5+(int)(Math.random()*5),TimeUnit.DAYS);
        return categoryVOS;
    }

    public String testLock() {

        RLock lock = this.redissonClient.getLock("lock");
        lock.lock();

        // 获取到锁执行业务逻辑
        String numString = this.redisTemplate.opsForValue().get("num");
        if (StringUtils.isBlank(numString)) {
            return null;
        }
        int num = Integer.parseInt(numString);
        this.redisTemplate.opsForValue().set("num", String.valueOf(++num));

        lock.unlock();

        return "已经增加成功";
    }


    public String testLock1() {
        // 所有请求，竞争锁
        String uuid = UUID.randomUUID().toString();
        Boolean lock = this.redisTemplate.opsForValue().setIfAbsent("lock", uuid, 10, TimeUnit.SECONDS);
        // 获取到锁执行业务逻辑
        if (lock) {
            String numString = this.redisTemplate.opsForValue().get("num");
            if (StringUtils.isBlank(numString)) {
                return null;
            }
            int num = Integer.parseInt(numString);
            this.redisTemplate.opsForValue().set("num", String.valueOf(++num));

            // 释放锁
            Jedis jedis = null;
            try {
                jedis = this.jedisPool.getResource();
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                jedis.eval(script, Arrays.asList("lock"), Arrays.asList(uuid));
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
//            this.redisTemplate.execute(new DefaultRedisScript<>(script), Arrays.asList("lock"), uuid);
//            if (StringUtils.equals(uuid, this.redisTemplate.opsForValue().get("lock"))){
//                this.redisTemplate.delete("lock");
//            }
        } else {
            // 没有获取到锁的请求进行重试
            try {
                TimeUnit.SECONDS.sleep(1);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "已经增加成功";
    }

    public String testRead() {
        RReadWriteLock readWriteLock = this.redissonClient.getReadWriteLock("readWriteLock");
        readWriteLock.readLock().lock(10l, TimeUnit.SECONDS);

        String msg = this.redisTemplate.opsForValue().get("msg");

//        readWriteLock.readLock().unlock();
        return msg;
    }

    public String testWrite() {
        RReadWriteLock readWriteLock = this.redissonClient.getReadWriteLock("readWriteLock");
        readWriteLock.writeLock().lock(10l, TimeUnit.SECONDS);

        String msg = UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set("msg", msg);

//        readWriteLock.writeLock().unlock();
        return "数据写入成功。。 " + msg;
    }

    public String latch() throws InterruptedException {

        RCountDownLatch latchDown = this.redissonClient.getCountDownLatch("latchDown");

//        String countString = this.redisTemplate.opsForValue().get("count");
//        int count = Integer.parseInt(countString);
        latchDown.trySetCount(5);

        latchDown.await();
        return "班长锁门。。。。。";
    }

    public String out() {
        RCountDownLatch latchDown = this.redissonClient.getCountDownLatch("latchDown");

//        String countString = this.redisTemplate.opsForValue().get("count");
//        int count = Integer.parseInt(countString);
//        this.redisTemplate.opsForValue().set("count", String.valueOf(--count));

        latchDown.countDown();
        return "出来了一个人。。。。";
    }
}
