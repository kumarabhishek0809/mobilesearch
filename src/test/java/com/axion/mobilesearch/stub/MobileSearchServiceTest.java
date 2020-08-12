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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MobileSearchServiceTest {

    private IMobileSearchService mobileSearchService;

    @BeforeEach
    public void start() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to customer object
        List<MobileSearchResponse> mobileSearchResponses =
                objectMapper.readValue(new File("./resouces/data.json"), new TypeReference<List<MobileSearchResponse>>() {
                });

        mobileSearchService = new MobileSearchService(new MobileSearchClient() {

            @Override
            public List<MobileSearchResponse> getPhones() {
                return mobileSearchResponses;
            }
        });
    }

    @Test
    public void getMobilesTest() {
        List<MobileSearchResponse> mobilePhones = mobileSearchService.getMobilePhones(200, null, null);
        assertEquals(mobilePhones.size(), 10);
        List<MobileSearchResponse> eSIM = mobileSearchService.getMobilePhones(null, "eSIM", null);
        assertEquals(eSIM.size(), 18);
        List<MobileSearchResponse> eSim1999 = mobileSearchService.getMobilePhones(null, "eSIM", "1999");
        assertEquals(eSim1999.size(), 2);
    }

}
