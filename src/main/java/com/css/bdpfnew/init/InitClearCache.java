package com.css.bdpfnew.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @Author lvmn
 * @Date 2018/12/10 10:47
 * @Description
 *  在项目启动之前和加载缓存之前需要先将之前的redis缓存清除
 */
//@Component
//@Order(2)
public class InitClearCache implements ApplicationRunner {
    private String CACHE_KEY_PREFIX = "com.css.bdpfnew";
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String pattern = CACHE_KEY_PREFIX + "*";
        RedisConnection connection = redisTemplate
                .getConnectionFactory().getConnection();
        Set<byte[]> caches = connection.keys(pattern.getBytes());
        if(!caches.isEmpty()){
            connection.del(caches.toArray(new byte[][]{}));
        }
        System.out.println("项目初始化清空已有缓存数据。");
    }
}
