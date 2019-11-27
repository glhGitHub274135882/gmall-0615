package com.atguigu.gmall.order.controller;

import com.alipay.api.AlipayApiException;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.oms.entity.OrderEntity;
import com.atguigu.gmall.oms.vo.OrderSubmitVO;
import com.atguigu.gmall.order.config.AlipayTemplate;
import com.atguigu.gmall.order.entity.UserInfo;
import com.atguigu.gmall.order.entity.vo.OrderConfirmVO;
import com.atguigu.gmall.order.entity.vo.PayAsyncVo;
import com.atguigu.gmall.order.entity.vo.PayVo;
import com.atguigu.gmall.order.entity.vo.SeckillVO;
import com.atguigu.gmall.order.interceptor.LoginInterceptor;
import com.atguigu.gmall.order.service.OrderService;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author shkstart
 * @create 2019-11-15 20:23
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("confirm")
    public Resp<OrderConfirmVO> confirm(){
        OrderConfirmVO orderConfirmVO = this.orderService.confirm();
        return Resp.ok(orderConfirmVO);
    }

    @PostMapping("submit")
    public Resp<Object> submit(@RequestBody OrderSubmitVO orderSubmitVO){
        String form = null;
        try {
            OrderEntity orderEntity = this.orderService.submit(orderSubmitVO);
            PayVo payVo = new PayVo();
            payVo.setBody("谷粒商城支付系统");
            payVo.setSubject("支付平台");
            payVo.setTotal_amount(orderEntity.getTotalAmount().toString());
            payVo.setOut_trade_no(orderEntity.getOrderSn());
            form = this.alipayTemplate.pay(payVo);
            System.out.println(form);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return Resp.ok(form);
    }
    @PostMapping("/pay/success")
    public Resp<Object> paySuccess(PayAsyncVo payAsyncVo){

        System.out.println("=======================支付成功==========================");
        // 订单状态的修改和库存的扣除
        orderService.paySucess(payAsyncVo.getOut_trade_no());

        return Resp.ok(null);
    }

    @RequestMapping("seckill/{skuId}")
    public Resp<Object> secKill(@PathVariable("skuId")Long skuId) throws InterruptedException {
        String stockJson = this.redisTemplate.opsForValue().get("seckill:stock" + skuId);
        if(StringUtils.isEmpty(stockJson)){
            return Resp.ok("秒杀已经结束");
        }

        Integer stock = Integer.valueOf(stockJson);
        RSemaphore semaphore = this.redissonClient.getSemaphore("seckill:lock" + skuId);
        semaphore.trySetPermits(stock);

        semaphore.acquire(1);
        UserInfo userInfo = LoginInterceptor.get();
        RCountDownLatch countDownLatch = this.redissonClient.getCountDownLatch("seckill:count" + userInfo.getUserId());
        countDownLatch.trySetCount(1);

        SeckillVO seckillVO = new SeckillVO();
        seckillVO.setCount(1);
        seckillVO.setSkuId(skuId);
        seckillVO.setUserId(userInfo.getUserId());

        this.amqpTemplate.convertAndSend("SECKILL-EXCHANGE","seckill.create");
        this.redisTemplate.opsForValue().set("seckill:stock"+skuId,String.valueOf(--stock));

        return Resp.ok(null);
    }

    @GetMapping
    public Resp<OrderEntity> queryOrder() throws InterruptedException {
        UserInfo userInfo = LoginInterceptor.get();

        RCountDownLatch countDownLatch = this.redissonClient.getCountDownLatch("seckill:count" + userInfo.getUserId());
        countDownLatch.await();
        OrderEntity orderEntity = this.orderService.queryOrder();

        return Resp.ok(orderEntity);
    }
}
