package com.example.holubsqlbackend.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.holubsqlbackend.infrastructure.holub.holubRepository;

@Service
@RequiredArgsConstructor
public class restaurantService {
    holubRepository holubRepository = new holubRepository();

    public String getRestaurant() {
        return holubRepository.getTable("select distinct last from name").toString();
    }
}