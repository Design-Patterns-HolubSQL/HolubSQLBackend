package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.UsesEntity;
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
public class UsesService {
    HolubRepository holubRepository = new HolubRepository();

    public List<UsesEntity> getUses() {
        Table table = holubRepository.getTable("select * from uses");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<UsesEntity> useEntities = new LinkedList<>();
        for (Object o : rowSet) {
            Object[] rowData = (Object[]) o;
            useEntities.add(UsesEntity.builder()
                    .uses_id(rowData[0].toString())
                    .ingredient_id(rowData[1].toString())
                    .menu_id(rowData[2].toString())
                    .build());
        }
        return useEntities;
    }

    public UsesEntity searchUseById(String id) {
        List<UsesEntity> useEntities = getUses();
        UsesEntity result = null;
        for (UsesEntity useEntity : useEntities) {
            if (useEntity.getUses_id().equals(id)) {
                result = useEntity;
                break;
            }
        }
        return result;
    }

    public List<UsesEntity> searchUsesByIngredientId(String id) {
        List<UsesEntity> useEntities = getUses();
        List<UsesEntity> results = new LinkedList<>();
        for (UsesEntity useEntity : useEntities) {
            if (useEntity.getIngredient_id().equals(id)) {
                results.add(useEntity);
            }
        }
        return results;
    }

    public List<UsesEntity> searchUsesByMenuId(String id) {
        List<UsesEntity> useEntities = getUses();
        List<UsesEntity> results = new LinkedList<>();
        for (UsesEntity useEntity : useEntities) {
            if (useEntity.getMenu_id().equals(id)) {
                results.add(useEntity);
            }
        }
        return results;
    }
}