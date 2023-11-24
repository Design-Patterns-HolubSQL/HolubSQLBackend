package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.RtDTO;

import java.util.ArrayList;
import java.util.List;

public class NameFilter extends TemplateRtFilter {
    @Override
    void filterRtList(Object filter) {
        List<RtDTO> result = new ArrayList<>();
        for(RtDTO rtEntity:rtEntities){
            if(rtEntity.getRestaurant_name().toLowerCase().contains(((String)filter).toLowerCase())){
                result.add(rtEntity);
            }
        }
        rtEntities = result;
    }
}
