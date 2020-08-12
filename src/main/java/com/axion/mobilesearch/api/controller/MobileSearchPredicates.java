package com.axion.mobilesearch.api.controller;

import com.axion.mobilesearch.api.response.MobileSearchResponse;

import java.util.function.Predicate;

public interface MobileSearchPredicates {


    static Predicate<MobileSearchResponse> getPredicatePriceEur(Integer priceEur) {
        return priceEur != null ? mobile -> mobile.getRelease().getPriceEur().intValue()
                == priceEur.intValue() : mobileSearchResponse -> true;
    }

    static Predicate<MobileSearchResponse> getPredicateAnnounceDate(String announceDate) {
        return announceDate != null ? mobile -> mobile.getRelease().getAnnounceDate().substring(0, 4)
                .equals(announceDate) : mobileSearchResponse -> true;
    }

    static Predicate<MobileSearchResponse> getPredicateSim(String sim) {
        return sim != null ? mobile -> mobile.getSim().contains(sim) :
                mobileSearchResponse -> true;
    }


}
