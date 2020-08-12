package com.axion.mobilesearch.api.fallback;

import com.axion.mobilesearch.api.client.MobileSearchClient;
import com.axion.mobilesearch.api.response.MobileSearchResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MobileSearchClientFallback implements MobileSearchClient {

    Logger logger = LoggerFactory.getLogger(MobileSearchClientFallback.class);

    private final Throwable cause;

    public MobileSearchClientFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<MobileSearchResponse> getPhones() {
        if (cause instanceof FeignException && ((FeignException) cause)
                .status() == 404) {
            logger.error("404 error took place when getPhones was called  Error message: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }
        return new ArrayList<>();
    }
}
