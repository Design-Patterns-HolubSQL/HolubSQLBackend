package com.example.holubsqlbackend.application.dto;

import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RtDTO {
    private String restaurant_id;
    private String restaurant_name;
    private String longitude;
    private String latitude;
    private String contact_number;
    private String region;
    private List<IngredientEntity> allergic_ingredients;

    @Builder
    public RtDTO(String restaurant_id, String restaurant_name, String longitude, String latitude, String contact_number, String region, List<IngredientEntity> allergic_ingredients) {
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.contact_number = contact_number;
        this.region = region;
        this.allergic_ingredients = allergic_ingredients;
    }
}
