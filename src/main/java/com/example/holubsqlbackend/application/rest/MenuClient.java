package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.*;
import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.domain.service.MenuService;
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
public class MenuClient {
    private final MenuService menuService;

    @GetMapping("/restaurantMenus")
    public ResponseEntity<MenuResponseDTO> getMenus(@RequestParam String id) {
        MenuResponseDTO menuResponseDTO = menuService.getMenus(id);
        return ResponseEntity.status(HttpStatus.OK).body(menuResponseDTO);
    }
}