package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.*;
import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.domain.service.IngredientService;
import com.example.holubsqlbackend.domain.service.UsesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IngredientClient {
    private final IngredientService ingredientService;
    private final UsesService usesService;

    @GetMapping("/ingredientsOfMenu")
    public ResponseEntity<IngredientResponseDTO> getIngredients(@RequestParam String id) {
        IngredientResponseDTO ingredientResponseDTO = ingredientService.getIngredients(id);
        return ResponseEntity.status(HttpStatus.OK).body(ingredientResponseDTO);
    }

}