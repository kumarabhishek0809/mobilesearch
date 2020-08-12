package com.axion.mobilesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableFeignClients
public class MobileSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileSearchApplication.class, args);
    }
    public static final String MOBILE_CATALOG = "PRODUCT_CATLOG";

    @Bean
    public CacheManager cacheManager(){
        Cache productCatlog = new ConcurrentMapCache(MOBILE_CATALOG);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(asList(productCatlog));
        return manager;
    }

    @CacheEvict(allEntries = true, value = {MOBILE_CATALOG})
    @Scheduled(fixedDelay = 60 * 60 * 1000 ,  initialDelay = 500)
    public void reportCacheEvict() {
        System.out.println("Flush Cache ");
    }


}
