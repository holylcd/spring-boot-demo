package org.holy.spring.boot.quick.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

/**
 * Redis cacheManager 统一配置
 * @author holy
 * @date 2019/9/09 15:46
 * @version 1.0.0
 */
@ConditionalOnProperty(name = "spring.redis")
@Slf4j
@Configuration
public class RedisCacheManagerConfig {

    /**
     * 通用 CacheManager，5 min TTL
     * @param factory
     * @return
     */
    @Bean
    public CacheManager commonCacheManager(LettuceConnectionFactory factory) {
        // 设置缓存有效期5分钟
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(5L));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

}