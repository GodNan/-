package com.css.bdpfnew.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//@Configuration
//@EnableCaching //换存开启注解，去掉缓存便不生效
public class RedisConfig extends CachingConfigurerSupport{

	 /*定义缓存数据 key 生成策略的bean ，可定义多个
    包名+类名+方法名+所有参数
    */
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }  
    @Bean
    public KeyGenerator objectid(){
    	return new KeyGenerator(){
    		@Override
    		public Object generate(Object target,Method method,Object... params){
    			 StringBuilder sb = new StringBuilder();
                 sb.append(target.getClass().getName()+":");
                 try{
                	sb.append(params[0].getClass().getMethod("getId", null).invoke(params[0],null).toString());
                 }catch(NoSuchMethodException no){
                	 no.printStackTrace();
                 }catch(IllegalAccessException il){
                	 il.printStackTrace();
                 }catch(InvocationTargetException iv){
                	 iv.printStackTrace();
                 }
                 return sb.toString();
    		}
    	};
    }

    /*要启用spring缓存支持,需创建一个 CacheManager的 bean，CacheManager 接口有很多实现，这里Redis 的集成，用 RedisCacheManager这个实现类 
    Redis 不是应用的共享内存，它只是一个内存服务器，就像 MySql 似的， 
    我们需要将应用连接到它并使用某种“语言”进行交互，因此我们还需要一个连接工厂以及一个 Spring 和 Redis 对话要用的 RedisTemplate， 
    这些都是 Redis 缓存所必需的配置，把它们都放在自定义的 CachingConfigurerSupport 中 
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
       // cacheManager.setDefaultExpiration(600);//设置缓存保留时间（seconds）
        return cacheManager;
    }

    //1.项目启动时此方法先被注册成bean被spring管理  
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
    	//创建一个模板类    将刚才的redis连接工厂设置到模板类中
    	StringRedisTemplate template = new StringRedisTemplate(factory);
    	// 设置value的序列化器
        //使用Jackson 2，将对象序列化为JSON
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //json转对象类，不设置默认的会将json转成hashmap
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                System.out.println(key);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                System.out.println(value);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
            	System.out.println(key);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {

            }
        };
        return cacheErrorHandler;
    }

}