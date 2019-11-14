package com.atguigu.gmall.item.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @create 2019-11-12 0:24
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        return new ThreadPoolExecutor(1000,5000,2,TimeUnit.SECONDS,new ArrayBlockingQueue<>(Integer.MAX_VALUE/10));

    }
}
