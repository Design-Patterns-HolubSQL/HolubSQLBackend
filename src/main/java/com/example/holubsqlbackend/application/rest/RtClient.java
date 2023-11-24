package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.RtDTO;
import com.example.holubsqlbackend.application.dto.RtResponseDTO;
import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.domain.entity.RtEntity;
import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RtClient {
    private final RtService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<RtResponseDTO> getRestaurants(
            @Nullable @RequestParam String name,
            @Nullable @RequestParam String tag1,
            @Nullable @RequestParam String tag2,
            @Nullable @RequestParam String tag3) {
        RtResponseDTO rtResponseDTO = restaurantService.getRestaurants(name, tag1, tag2, tag3);
        return ResponseEntity.status(HttpStatus.OK).body(rtResponseDTO);
    }

    @GetMapping("/restaurantDetails")
    public ResponseEntity<RtResponseDTO> getRestaurantDetails(@RequestParam String id) {
        RtResponseDTO rtResponseDTO = restaurantService.getRestaurantDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(rtResponseDTO);
    }
}