package org.holy.spring.boot.quick.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * RedisTemplate 统一配置
 * @author holy
 * @date 2019/9/09 15:46
 * @version 1.0.0
 */
@ConditionalOnProperty(name = "spring.redis")
@Slf4j
@Configuration
public class RedisTemplateConfig {

    /**
     * RedisTemplate<String, Serializable>
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> stringSerializableRedisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        // set key serializer
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        template.setKeySerializer(keySerializer);
        template.setHashKeySerializer(keySerializer);
        // value serializer
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(valueSerializer);
        template.setHashValueSerializer(valueSerializer);

        // factory
        template.setConnectionFactory(factory);

        // 建议调用下这方法
        template.afterPropertiesSet();
        return template;
    }

}