package com.axion.mobilesearch.api.service;

import com.axion.mobilesearch.api.response.MobileSearchResponse;

import java.util.List;

public interface IMobileSearchService {
    List<MobileSearchResponse> getMobilePhones(Integer priceEur, String sim, String announceDate);
}
