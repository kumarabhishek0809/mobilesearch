package com.axion.mobilesearch.api.controller;

import com.axion.mobilesearch.api.response.MobileSearchResponse;
import com.axion.mobilesearch.api.service.IMobileSearchService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1")
public class MobileSearchController {

    private final IMobileSearchService mobileSearchService;

    public MobileSearchController(IMobileSearchService mobileSearchService) {
        this.mobileSearchService = mobileSearchService;
    }

    @GetMapping("/mobile/search")
    public List<MobileSearchResponse> getMobileSearch(
            @RequestParam(required = false, name = "priceEur") Integer priceEur,
            @RequestParam(required = false, name = "sim") String sim,
            @RequestParam(required = false, name = "announceDate") String announceDate,
            @RequestParam(required = false, name = "price") Integer price) {
        if (price != null) {
            priceEur = price;
        }
        List<MobileSearchResponse> mobilePhones = mobileSearchService.getMobilePhones(priceEur, sim, announceDate);
        System.out.println(mobilePhones.size());
        return mobilePhones;
    }

}
