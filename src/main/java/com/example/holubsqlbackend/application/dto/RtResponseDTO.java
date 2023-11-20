package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RtResponseDTO {
    private List<RtDTO> restaurant;

    @Builder
    public RtResponseDTO(List<RtDTO> restaurant) {
        this.restaurant = restaurant;
    }
}
