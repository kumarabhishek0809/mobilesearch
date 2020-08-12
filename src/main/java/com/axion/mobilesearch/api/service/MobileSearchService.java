package com.axion.mobilesearch.api.service;

import com.axion.mobilesearch.api.client.MobileSearchClient;
import com.axion.mobilesearch.api.response.MobileSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.axion.mobilesearch.api.controller.MobileSearchPredicates.*;

@Service
public class MobileSearchService implements IMobileSearchService {

    private final MobileSearchClient mobileSearchClient;

    public MobileSearchService(MobileSearchClient mobileSearchClient) {
        this.mobileSearchClient = mobileSearchClient;
    }

    @Override
    public List<MobileSearchResponse> getMobilePhones(Integer priceEur, String sim, String announceDate) {

        return mobileSearchClient.getPhones().stream()
                .filter(getPredicatePriceEur(priceEur))
                .filter(getPredicateSim(sim))
                .filter(getPredicateAnnounceDate(announceDate))
                .collect(Collectors.toList());
    }
}
