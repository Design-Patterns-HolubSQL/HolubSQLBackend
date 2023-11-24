package com.example.holubsqlbackend.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuEntity {
    private String menu_id;
    private String restaurant_id;
    private String menu_name;
    private String price;
    @Builder
    public MenuEntity(String menu_id, String restaurant_id, String menu_name, String price) {
        this.menu_id = menu_id;
        this.restaurant_id = restaurant_id;
        this.menu_name = menu_name;
        this.price = price;
    }
}
