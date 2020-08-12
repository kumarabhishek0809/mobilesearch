package com.axion.mobilesearch.api.response;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class MobileSearchResponse {

    private String phone;
    private Release release;
    private String sim;
    private Integer id;
    private String brand;
    private String resolution;
    private String picture;
    private Hardware hardware;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Release {
        private Integer priceEur;
        private String announceDate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Hardware {
        private String audioJack;
        private String gps;
        private String battery;
    }

}
