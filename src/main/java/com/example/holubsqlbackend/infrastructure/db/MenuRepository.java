package com.example.holubsqlbackend.infrastructure.db;

import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class MenuRepository {
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
                    .build());
        }
        return menuEntities;
    }
}
