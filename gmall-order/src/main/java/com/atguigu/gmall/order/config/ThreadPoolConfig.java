package com.atguigu.gmall.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2019-11-15 18:00
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(@Value("${order.threadPool.corePoolSize}") Integer corePoolSize
            , @Value("${order.threadPool.maximunPoolSize}") Integer maximunPoolSize
            , @Value("${order.threadPool.keepAliveTime}") Integer keepAliveTime){

        return new ThreadPoolExecutor(corePoolSize, maximunPoolSize, keepAliveTime,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(Integer.MAX_VALUE/10));
    }
}