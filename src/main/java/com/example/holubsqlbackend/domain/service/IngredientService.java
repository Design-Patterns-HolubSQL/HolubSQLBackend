package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.infrastructure.db.IngredientRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public IngredientEntity searchIngredientById(String id) {
        List<IngredientEntity> ingredientEntities = ingredientRepository.getIngredients();
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
        List<IngredientEntity> ingredientEntities = ingredientRepository.getIngredients();
        List<IngredientEntity> results = new ArrayList<>();
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            if (ids.contains(ingredientEntity.getIngredient_id())) {
                results.add(ingredientEntity);
            }
        }
        return results;
    }
}