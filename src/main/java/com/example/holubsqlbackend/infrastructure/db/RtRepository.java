package com.example.holubsqlbackend.infrastructure.db;

import com.example.holubsqlbackend.domain.entity.RtEntity;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class RtRepository {
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
}
