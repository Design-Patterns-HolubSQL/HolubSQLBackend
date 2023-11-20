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
    public ResponseEntity<MenuResponseDTO> getIngredients(@RequestParam String id) {

        List<MenuEntity> menuEntities = menuService.searchMenusByRestaurantId(id);
        List<MenuDTO> menuDTO = new ArrayList<>();

        for (MenuEntity menuEntity : menuEntities) {
            menuDTO.add(MenuDTO.builder()
                    .restaurant_id(menuEntity.getRestaurant_id())
                    .price(menuEntity.getPrice())
                    .picture(menuEntity.getPicture())
                    .menu_name(menuEntity.getMenu_name())
                    .menu_id(menuEntity.getMenu_id())
                    .build());
        }

        MenuResponseDTO menuResponseDTO = MenuResponseDTO.builder()
                .menu(menuDTO)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(menuResponseDTO);
    }
}