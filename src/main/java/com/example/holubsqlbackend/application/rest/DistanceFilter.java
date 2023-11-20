package com.example.holubsqlbackend.application.rest;

import com.example.holubsqlbackend.application.dto.RtDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistanceFilter extends TemplateRtFilter {
    @Override
    void filterRtList(Object filter) {
        List<String> parsedFilter = new ArrayList<>(Arrays.asList(filter.toString().split(",")));
        int maxDistance = Integer.parseInt(parsedFilter.get(0));
        List<RtDTO> result = new ArrayList<>();
        for(RtDTO rtEntity:rtEntities){
            double longitudeDiff = Double.parseDouble(rtEntity.getLongitude()) - Double.parseDouble(parsedFilter.get(1));
            double latitudeDiff = Double.parseDouble(rtEntity.getLatitude()) - Double.parseDouble(parsedFilter.get(2));

            //todo: divide by 100000(?) to convert to meter - change restaurants' position before testing
            double distance = Math.sqrt(longitudeDiff*longitudeDiff + latitudeDiff*latitudeDiff);
            if(distance <= maxDistance){
                result.add(rtEntity);
            }
        }
        rtEntities = result;
    }
}