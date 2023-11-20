package com.example.holubsqlbackend.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UsesResponseDTO {
    private List<UsesDTO> use;

    @Builder
    public UsesResponseDTO(List<UsesDTO> use) {
        this.use = use;
    }
}
