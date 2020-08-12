package com.axion.mobilesearch.stub;

import com.axion.mobilesearch.api.client.MobileSearchClient;
import com.axion.mobilesearch.api.response.MobileSearchResponse;
import com.axion.mobilesearch.api.service.IMobileSearchService;
import com.axion.mobilesearch.api.service.MobileSearchService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MobileSearchServiceTest {

    private IMobileSearchService mobileSearchService;

    @BeforeEach
    public void start() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to customer object
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/data.json");
        List<MobileSearchResponse> mobileSearchResponses =
                objectMapper.readValue(resourceAsStream,
                        new TypeReference<List<MobileSearchResponse>>() {
                        });

        mobileSearchService = new MobileSearchService(new MobileSearchClient() {

            @Override
            public List<MobileSearchResponse> getPhones() {
                return mobileSearchResponses;
            }
        });
    }


    @Test
    public void predicatePhonePriceTest() {
        List<MobileSearchResponse> mobilePhones = mobileSearchService.getMobilePhones(200, null, null);
        assertEquals(mobilePhones.size(), 10);
    }

    @Test
    public void predicatePhoneEsimTest() {
        List<MobileSearchResponse> eSIM = mobileSearchService.getMobilePhones(null, "eSIM", null);
        assertEquals(eSIM.size(), 18);
    }

    @Test
    public void predicatePhoneEsimYearLaunchTest() {
        List<MobileSearchResponse> eSim1999 = mobileSearchService.getMobilePhones(200, null, "1999");
        assertEquals(eSim1999.size(), 2);
    }


}
