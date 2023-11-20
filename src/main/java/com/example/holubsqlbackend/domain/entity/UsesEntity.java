package com.example.holubsqlbackend.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UsesEntity {
    private String uses_id;
    private String ingredient_id;
    private String menu_id;

    @Builder
    public UsesEntity(String uses_id, String ingredient_id, String menu_id) {
        this.uses_id = uses_id;
        this.ingredient_id = ingredient_id;
        this.menu_id = menu_id;
    }
}
