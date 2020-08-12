package com.axion.mobilesearch.api.client;

import com.axion.mobilesearch.MobileSearchApplication;
import com.axion.mobilesearch.api.fallback.MobileSearchFallbackFactory;
import com.axion.mobilesearch.api.response.MobileSearchResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "mobilesearch", url = "${mobile.search.uri}" ,
        fallbackFactory = MobileSearchFallbackFactory.class,
        configuration = MobileSearchFeignConfig.class)
public interface MobileSearchClient {

    @RequestMapping(method = RequestMethod.GET)
    @Cacheable(value = MobileSearchApplication.MOBILE_CATALOG, key = " 'getPhones' ")
    List<MobileSearchResponse> getPhones();
}
