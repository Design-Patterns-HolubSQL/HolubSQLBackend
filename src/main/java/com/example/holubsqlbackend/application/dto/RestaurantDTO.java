package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RestaurantDTO {
    private String first;
    private String last;
    private String addrId;

    @Builder
    public RestaurantDTO(String first, String last, String addrId) {
        this.first = first;
        this.last = last;
        this.addrId = addrId;
    }
}
