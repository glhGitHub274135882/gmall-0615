package com.atguigu.gmall.index.annotation;

import java.lang.annotation.*;

/**
 * @author shkstart
 * @create 2019-11-10 23:16
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmallCache {

    /**
     * 缓存前缀
     * @return
     */
    String prefix() default "cache";

    /**
     * 单位是秒
     * @return
     */
    long timeout() default 300l;

    /**
     * 为了防止缓存雪崩，而设置的过期时间的随机值范围
     * @return
     */
    long random() default 300l;
}
