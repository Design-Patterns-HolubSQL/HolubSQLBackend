package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.infrastructure.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    HolubRepository holubRepository = new HolubRepository();

    public List<MenuEntity> getMenus() {
        Table table = holubRepository.getTable("select * from menu");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<MenuEntity> menuEntities = new LinkedList<>();
        for (int i = 0; i < rowSet.size(); i++) {
            Object[] rowData = (Object[]) rowSet.get(i);
            menuEntities.add(MenuEntity.builder()
                    .menu_id(rowData[0].toString())
                    .restaurant_id(rowData[1].toString())
                    .menu_name(rowData[2].toString())
                    .price(rowData[3].toString())
                    .picture(rowData[4].toString())
                    .build());
        }
        return menuEntities;
    }
    public MenuEntity searchMenuById(String id) {
        List<MenuEntity> menuEntities = getMenus();
        MenuEntity result = null;
        for (MenuEntity menuEntity : menuEntities) {
            if (menuEntity.getMenu_id().equals(id)) {
                result = menuEntity;
                break;
            }
        }
        return result;
    }

    public List<MenuEntity> searchMenusByRestaurantId(String id) {
        List<MenuEntity> menuEntities = getMenus();
        List<MenuEntity> results = new LinkedList<>();
        for (int i = 0; i < menuEntities.size(); i++) {
            if (menuEntities.get(i).getRestaurant_id().equals(id)) {
                results.add(menuEntities.get(i));
            }
        }
        return results;
    }
}