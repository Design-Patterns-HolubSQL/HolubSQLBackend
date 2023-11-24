package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.application.dto.RtDTO;
import com.example.holubsqlbackend.domain.entity.IngredientEntity;

import java.util.ArrayList;
import java.util.List;

public class AllergyFilter extends TemplateRtFilter {
    @Override
    void filterRtList(Object filter) {
        List<RtDTO> result = new ArrayList<>();
        for(RtDTO rtEntity:rtEntities){
            boolean dangerous_flag = false;
            for(IngredientEntity ingredientEntity: rtEntity.getAllergic_ingredients()){
                if(ingredientEntity.getIngredient_name().equals(filter)){
                    dangerous_flag = true;
                    break;
                }
            }
            if(!dangerous_flag) {
                result.add(rtEntity);
            }
        }
        rtEntities = result;
    }
}
