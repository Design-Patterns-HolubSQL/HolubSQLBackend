package com.example.holubsqlbackend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class restaurantService {
    public String getRestaurant() {
        return "restaurant1";
    }
}