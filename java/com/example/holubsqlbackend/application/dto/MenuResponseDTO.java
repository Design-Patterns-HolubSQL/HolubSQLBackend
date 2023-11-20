package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MenuResponseDTO {
    private List<MenuDTO> menu;

    @Builder
    public MenuResponseDTO(List<MenuDTO> menu) {
        this.menu = menu;
    }
}
