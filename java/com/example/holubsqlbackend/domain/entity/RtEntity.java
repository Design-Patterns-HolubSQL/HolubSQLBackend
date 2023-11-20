package com.example.holubsqlbackend.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RtEntity {
    private String restaurant_id;
    private String restaurant_name;
    private String longitude;
    private String latitude;
    private String contact_number;
    private String region;

    @Builder
    public RtEntity(String restaurant_id, String restaurant_name, String longitude, String latitude, String contact_number, String region) {
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contact_number = contact_number;
        this.region = region;
    }
}
