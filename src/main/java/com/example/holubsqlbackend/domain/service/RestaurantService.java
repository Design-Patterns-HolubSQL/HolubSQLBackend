package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.NameEntity;
import com.example.holubsqlbackend.infrastructure.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.holub.database.UnmodifiableTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.holubsqlbackend.infrastructure.holub.HolubRepository;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    HolubRepository holubRepository = new HolubRepository();

    public List<NameEntity> getRestaurant() {
        Table table = holubRepository.getTable("select * from name");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<NameEntity> nameEntities = new LinkedList<>();
        for (int i = 0; i < rowSet.size(); i++) {
            Object[] rowData = (Object[]) rowSet.get(i);
            nameEntities.add(NameEntity.builder()
                    .first(rowData[0].toString())
                    .last(rowData[1].toString())
                    .addrId(rowData[2].toString())
                    .build());
        }
        return nameEntities;
    }
}