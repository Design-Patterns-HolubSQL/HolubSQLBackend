package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.infrastructure.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    HolubRepository holubRepository = new HolubRepository();

    public List<IngredientEntity> getIngredients() {
        Table table = holubRepository.getTable("select * from ingredient");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<IngredientEntity> ingredientEntities = new LinkedList<>();
        for (Object o : rowSet) {
            Object[] rowData = (Object[]) o;
            ingredientEntities.add(IngredientEntity.builder()
                    .ingredient_id(rowData[0].toString())
                    .ingredient_name(rowData[1].toString())
                    .allergic(rowData[2].toString().equals("true"))
                    .build());
        }
        return ingredientEntities;
    }
    public IngredientEntity searchIngredientById(String id) {
        List<IngredientEntity> ingredientEntities = getIngredients();
        IngredientEntity result = null;
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            if (ingredientEntity.getIngredient_id().equals(id)) {
                result = ingredientEntity;
                break;
            }
        }
        return result;
    }
    public List<IngredientEntity> searchIngredientsByIds(List<String> ids) {
        List<IngredientEntity> ingredientEntities = getIngredients();
        List<IngredientEntity> results = new ArrayList<>();
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            if (ids.contains(ingredientEntity.getIngredient_id())) {
                results.add(ingredientEntity);
            }
        }
        return results;
    }
}