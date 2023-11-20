package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.RtDTO;
import com.example.holubsqlbackend.application.dto.RtResponseDTO;
import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.domain.entity.MenuEntity;
import com.example.holubsqlbackend.domain.entity.RtEntity;
import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.domain.service.IngredientService;
import com.example.holubsqlbackend.domain.service.MenuService;
import com.example.holubsqlbackend.domain.service.RtService;
import com.example.holubsqlbackend.domain.service.UsesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RtClient {
    private final RtService restaurantService;
    private final MenuService menuService;
    private final IngredientService ingredientService;
    private final UsesService usesService;

    @GetMapping("/restaurants")
    public ResponseEntity<RtResponseDTO> getRestaurants(
            @Nullable @RequestParam String name,
            @Nullable @RequestParam String tag1,
            @Nullable @RequestParam String tag2,
            @Nullable @RequestParam String tag3) {
        List<RtEntity> restaurants = restaurantService.getRts();
        List<RtDTO> rtDTO = new ArrayList<>();
        for (RtEntity rtEntity : restaurants) {
            List<UsesEntity> usesEntities = new ArrayList<>();
            for (MenuEntity menuEntity : menuService.searchMenusByRestaurantId(rtEntity.getRestaurant_id())){
                usesEntities.addAll(usesService.searchUsesByMenuId(menuEntity.getMenu_id()));
            }
            Set<String> ingredientIds = new HashSet<>();
            for(UsesEntity usesEntity : usesEntities){
                ingredientIds.add(usesEntity.getIngredient_id());
            }
            List<IngredientEntity> ingredientEntities = new ArrayList<>();
            List<String> list = new ArrayList<>(ingredientIds);
            for(IngredientEntity ingredientEntity : ingredientService.searchIngredientsByIds(list)){
                if(ingredientEntity.getAllergic()) {
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


        if(name != null){
            TemplateRtFilter filter = new NameFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(name);
            rtDTO = filter.getFilteredResult();
        }
        if(tag1 != null){
            TemplateRtFilter filter = new AllergyFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(tag1);
            rtDTO = filter.getFilteredResult();
        }
        if(tag2 != null){
            TemplateRtFilter filter = new DistanceFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(tag2);
            rtDTO = filter.getFilteredResult();
        }
        if(tag3 != null){
            TemplateRtFilter filter = new RegionFilter();
            filter.setRtList(rtDTO);
            filter.filterRtList(tag3);
            rtDTO = filter.getFilteredResult();
        }

        RtResponseDTO rtResponseDTO = RtResponseDTO.builder()
                .restaurant(rtDTO)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(rtResponseDTO);
    }

    @GetMapping("/restaurantDetails")
    public ResponseEntity<RtResponseDTO> getRestaurantDetails(@RequestParam String id) {
        List<RtEntity> restaurant = new ArrayList<>(Collections.singletonList(restaurantService.searchRtById(id)));
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
        return ResponseEntity.status(HttpStatus.OK).body(rtResponseDTO);
    }
}