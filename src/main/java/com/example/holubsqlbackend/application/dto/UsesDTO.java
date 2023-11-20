package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UsesDTO {
    private String uses_id;
    private String ingredient_id;
    private String menu_id;

    @Builder
    public UsesDTO(String uses_id, String ingredient_id, String menu_id) {
        this.uses_id = uses_id;
        this.ingredient_id = ingredient_id;
        this.menu_id = menu_id;
    }
}
