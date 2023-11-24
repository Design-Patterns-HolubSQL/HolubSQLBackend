package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.infrastructure.db.MenuRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

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