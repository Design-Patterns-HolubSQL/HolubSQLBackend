package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.RtEntity;
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
public class RtService {
    HolubRepository holubRepository = new HolubRepository();

    public List<RtEntity> getRts() {
        Table table = holubRepository.getTable("select * from restaurant");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<RtEntity> rtEntities = new LinkedList<>();
        for (Object o : rowSet) {
            Object[] rowData = (Object[]) o;
            rtEntities.add(RtEntity.builder()
                    .restaurant_id(rowData[0].toString())
                    .restaurant_name(rowData[1].toString())
                    .longitude(rowData[2].toString())
                    .latitude(rowData[3].toString())
                    .contact_number(rowData[4].toString())
                    .region(rowData[5].toString())
                    .build());
        }
        return rtEntities;
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