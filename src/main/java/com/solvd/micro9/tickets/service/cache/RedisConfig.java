package com.solvd.micro9.tickets.service.cache;

import com.solvd.micro9.tickets.domain.cache.TicketCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    public final static String CACHE_KEY = "tickets";

    @Bean
    public ReactiveRedisOperations<String, TicketCache> reactiveRedisOperations(LettuceConnectionFactory connections) {
        final RedisSerializationContext<String, TicketCache> serializationContext = RedisSerializationContext
                .<String, TicketCache>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new GenericToStringSerializer<>(TicketCache.class))
                .hashKey(new Jackson2JsonRedisSerializer<>(Long.class))
                .hashValue(new GenericJackson2JsonRedisSerializer())
                .build();
        return new ReactiveRedisTemplate<>(connections, serializationContext);
    }

}
