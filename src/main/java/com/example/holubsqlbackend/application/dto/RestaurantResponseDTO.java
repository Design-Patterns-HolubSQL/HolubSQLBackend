package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RestaurantResponseDTO {
    private List<RestaurantDTO> restaurant;

    @Builder
    public RestaurantResponseDTO(List<RestaurantDTO> restaurant) {
        this.restaurant = restaurant;
    }
}
