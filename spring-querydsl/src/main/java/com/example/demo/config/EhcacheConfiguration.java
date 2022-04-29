package com.example.demo.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.impl.config.event.DefaultCacheEventListenerConfiguration;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.Set;

@EnableCaching
@Configuration
public class EhcacheConfiguration {

  @Bean
  public CacheManager ehcacheManager() {

    DefaultCacheEventListenerConfiguration cacheEventListenerConfiguration =
        new DefaultCacheEventListenerConfiguration(
            Set.of(EventType.CREATED, EventType.REMOVED, EventType.UPDATED, EventType.EXPIRED),
            new CacheEventLogger());

    CacheConfiguration<Object, Object> cacheConfiguration =
        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                    .heap(2, MemoryUnit.MB)
                    .offheap(10, MemoryUnit.MB)
                    .build())
            .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
            .add(cacheEventListenerConfiguration)
            .build();

    CachingProvider cachingProvider = Caching.getCachingProvider();

    CacheManager cacheManager = cachingProvider.getCacheManager();

    javax.cache.configuration.Configuration<Object, Object> configuration =
        Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);

    cacheManager.createCache("cacheStore", configuration);

    return cacheManager;
  }
}
