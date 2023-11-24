package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.application.dto.MenuDTO;
import com.example.holubsqlbackend.application.dto.MenuResponseDTO;
import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.infrastructure.db.MenuRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuResponseDTO getMenus(String id) {

        List<MenuEntity> menuEntities = searchMenusByRestaurantId(id);
        List<MenuDTO> menuDTO = new ArrayList<>();

        for (MenuEntity menuEntity : menuEntities) {
            menuDTO.add(MenuDTO.builder()
                    .restaurant_id(menuEntity.getRestaurant_id())
                    .price(menuEntity.getPrice())
                    .menu_name(menuEntity.getMenu_name())
                    .menu_id(menuEntity.getMenu_id())
                    .build());
        }

        MenuResponseDTO menuResponseDTO = MenuResponseDTO.builder()
                .menu(menuDTO)
                .build();
        return menuResponseDTO;
    }
    public List<MenuEntity> searchMenusByRestaurantId(String id) {
        List<MenuEntity> menuEntities = menuRepository.getMenus();
        List<MenuEntity> results = new LinkedList<>();
        for (int i = 0; i < menuEntities.size(); i++) {
            if (menuEntities.get(i).getRestaurant_id().equals(id)) {
                results.add(menuEntities.get(i));
            }
        }
        return results;
    }
}