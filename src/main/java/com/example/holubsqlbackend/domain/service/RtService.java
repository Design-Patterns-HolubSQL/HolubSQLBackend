package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.application.dto.RtDTO;
import com.example.holubsqlbackend.application.dto.RtResponseDTO;
import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.domain.entity.RtEntity;
import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.infrastructure.db.RtRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RtService {
    private final RtRepository rtRepository;
    private final MenuService menuService;
    private final IngredientService ingredientService;
    private final UsesService usesService;

    public RtResponseDTO getRestaurants(String name, String tag1, String tag2, String tag3) {
        List<RtEntity> restaurants = rtRepository.getRts();
        List<RtDTO> rtDTO = new ArrayList<>();
        for (RtEntity rtEntity : restaurants) {
            List<UsesEntity> usesEntities = new ArrayList<>();
            for (MenuEntity menuEntity : menuService.searchMenusByRestaurantId(rtEntity.getRestaurant_id())) {
                usesEntities.addAll(usesService.searchUsesByMenuId(menuEntity.getMenu_id()));
            }
            Set<String> ingredientIds = new HashSet<>();
            for (UsesEntity usesEntity : usesEntities) {
                ingredientIds.add(usesEntity.getIngredient_id());
            }
            List<IngredientEntity> ingredientEntities = new ArrayList<>();
            List<String> list = new ArrayList<>(ingredientIds);
            for (IngredientEntity ingredientEntity : ingredientService.searchIngredientsByIds(list)) {
                if (ingredientEntity.getAllergic()) {
                    ingredientEntities.add(ingredientEntity);
                }
            }

            rtDTO.add(RtDTO.builder()
                    .restaurant_id(rtEntity.getRestaurant_id())
                    .restaurant_name(rtEntity.getRestaurant_name())
                    .region(rtEntity.getRegion())
                    .longitude(rtEntity.getLongitude())
                    .latitude(rtEntity.getLatitude())
                    .contact_number(rtEntity.getContact_number())
                    .allergic_ingredients(ingredientEntities)
                    .build());
        }


        if (name != null) {
            TemplateRtFilter filter = new NameFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(name);
            rtDTO = filter.getFilteredResult();
        }
        if (tag1 != null) {
            TemplateRtFilter filter = new AllergyFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(tag1);
            rtDTO = filter.getFilteredResult();
        }
        if (tag2 != null) {
            TemplateRtFilter filter = new DistanceFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(tag2);
            rtDTO = filter.getFilteredResult();
        }
        if (tag3 != null) {
            TemplateRtFilter filter = new RegionFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(tag3);
            rtDTO = filter.getFilteredResult();
        }

        RtResponseDTO rtResponseDTO = RtResponseDTO.builder()
                .restaurant(rtDTO)
                .build();
        return rtResponseDTO;
    }
    public RtResponseDTO getRestaurantDetails(String id) {
        List<RtEntity> restaurant = new ArrayList<>(Collections.singletonList(searchRtById(id)));
        List<RtDTO> rtDTO = new ArrayList<>();
        for (RtEntity rtEntity : restaurant) {
            rtDTO.add(RtDTO.builder()
                    .restaurant_id(rtEntity.getRestaurant_id())
                    .restaurant_name(rtEntity.getRestaurant_name())
                    .region(rtEntity.getRegion())
                    .longitude(rtEntity.getLongitude())
                    .latitude(rtEntity.getLatitude())
                    .contact_number(rtEntity.getContact_number())
                    .build());
        }
        RtResponseDTO rtResponseDTO = RtResponseDTO.builder()
                .restaurant(rtDTO)
                .build();
        return rtResponseDTO;
    }

    public RtEntity searchRtById(String id) {
        List<RtEntity> rtEntities = rtRepository.getRts();
        RtEntity result = null;
        for (RtEntity rtEntity : rtEntities) {
            if (rtEntity.getRestaurant_id().equals(id)) {
                result = rtEntity;
                break;
            }
        }
        return result;
    }

}