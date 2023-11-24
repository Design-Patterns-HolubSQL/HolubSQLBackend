package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.RtEntity;
import com.example.holubsqlbackend.infrastructure.db.RtRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RtService {
    final private RtRepository rtRepository;

    public List<RtEntity> getRts() {
        return rtRepository.getRts();
    }
    public RtEntity searchRtById(String id) {
        List<RtEntity> rtEntities = getRts();
        RtEntity result = null;
        for (RtEntity rtEntity : rtEntities) {
            if (rtEntity.getRestaurant_id().equals(id)) {
                result = rtEntity;
                break;
            }
        }
        return result;
    }

    public List<RtEntity> searchRtsByRtName(String name) {
        List<RtEntity> rtEntities = getRts();
        List<RtEntity> results = new LinkedList<>();
        for (RtEntity rtEntity : rtEntities) {
            if (rtEntity.getRestaurant_name().contains(name)) {
                results.add(rtEntity);
            }
        }
        return results;
    }
    public List<RtEntity> searchRtsByRegion(String region) {
        List<RtEntity> rtEntities = getRts();
        List<RtEntity> results = new LinkedList<>();
        for (RtEntity rtEntity : rtEntities) {
            if (rtEntity.getRegion().equals(region)) {
                results.add(rtEntity);
            }
        }
        return results;
    }
}