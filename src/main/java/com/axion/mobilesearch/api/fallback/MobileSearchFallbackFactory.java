package com.axion.mobilesearch.api.fallback;

import com.axion.mobilesearch.api.client.MobileSearchClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MobileSearchFallbackFactory implements FallbackFactory<MobileSearchClient> {
    @Override
    public MobileSearchClient create(Throwable cause) {
        return new MobileSearchClientFallback(cause);
    }


}
