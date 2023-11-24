package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.application.dto.IngredientDTO;
import com.example.holubsqlbackend.application.dto.IngredientResponseDTO;
import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.infrastructure.db.IngredientRepository;
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
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final UsesService usesService;
    public IngredientResponseDTO getIngredients(String id) {

        List<UsesEntity> usesEntities = usesService.searchUsesByMenuId(id);

        List<IngredientEntity> ingredientEntities = new ArrayList<>();
        for (UsesEntity usesEntity : usesEntities) {
            ingredientEntities.add(searchIngredientById(usesEntity.getIngredient_id()));
        }

        List<IngredientDTO> ingredientDTO = new ArrayList<>();
        for (IngredientEntity ingredientEntity : ingredientEntities) {
            ingredientDTO.add(IngredientDTO.builder()
                    .ingredient_id(ingredientEntity.getIngredient_id())
                    .ingredient_name(ingredientEntity.getIngredient_name())
                    .allergic(ingredientEntity.getAllergic())
                    .build());
        }

        IngredientResponseDTO ingredientResponseDTO = IngredientResponseDTO.builder()
                .ingredient(ingredientDTO)
                .build();
        return ingredientResponseDTO;
    }

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