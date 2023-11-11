package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.RestaurantDTO;
import com.example.holubsqlbackend.application.dto.RestaurantResponseDTO;
import com.example.holubsqlbackend.domain.entity.NameEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.holubsqlbackend.domain.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestaurantClient {
    private final RestaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<RestaurantResponseDTO> createRestaurant() {
        List<NameEntity> restaurant = restaurantService.getRestaurant();
        List<RestaurantDTO> restaurantDTO = new ArrayList<>();
        for(int i = 0; i < restaurant.size(); i++){
            restaurantDTO.add(RestaurantDTO.builder()
                    .first(restaurant.get(i).getFirst())
                    .last(restaurant.get(i).getLast())
                    .addrId(restaurant.get(i).getAddrId())
                    .build());
        }
        RestaurantResponseDTO restaurantResponseDTO = RestaurantResponseDTO.builder()
                .restaurant(restaurantDTO)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseDTO);
    }
}