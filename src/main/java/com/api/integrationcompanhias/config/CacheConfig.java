package com.api.integrationcompanhias.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final Duration CACHE_EXPIRATION = Duration.ofHours(1);

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(CACHE_EXPIRATION);

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public CacheCleaner cacheCleaner(RedisCacheManager cacheManager) {
        return new CacheCleaner(cacheManager);
    }

    public static class CacheCleaner {
        private final RedisCacheManager cacheManager;
        public CacheCleaner(RedisCacheManager cacheManager) {
            this.cacheManager = cacheManager;
            scheduleCacheCleanup();
        }
        private void scheduleCacheCleanup() {
            Thread cleanupThread = new Thread(() -> {
                try {
                    Thread.sleep(CACHE_EXPIRATION.toMillis());
                    cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            cleanupThread.setDaemon(true);
            cleanupThread.start();
        }
    }
}
