package com.atguigu.gmall.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author shkstart
 * @create 2019-11-10 21:18
 */
@Configuration
public class GmallJedisConfig {

    @Bean
    public JedisPool jedisPool(){

        return new JedisPool("192.168.127.128", 6379);
    }
}