package com.example.holubsqlbackend.application.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.holubsqlbackend.domain.service.restaurantService;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class restaurantClient {
    private final restaurantService restaurantService;

    @GetMapping("")
    public ResponseEntity<String> createBlock() {
        String restaurant = restaurantService.getRestaurant();
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }
}