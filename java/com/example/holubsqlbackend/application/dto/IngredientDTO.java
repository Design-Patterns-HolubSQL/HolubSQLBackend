package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IngredientDTO {
    private String ingredient_id;
    private String ingredient_name;
    private Boolean allergic;

    @Builder
    public IngredientDTO(String ingredient_id, String ingredient_name, Boolean allergic) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
        this.allergic = allergic;
    }
}
