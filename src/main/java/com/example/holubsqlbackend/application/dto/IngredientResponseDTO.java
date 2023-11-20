package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class IngredientResponseDTO {
    private List<IngredientDTO> ingredient;

    @Builder
    public IngredientResponseDTO(List<IngredientDTO> ingredient) {
        this.ingredient = ingredient;
    }
}
