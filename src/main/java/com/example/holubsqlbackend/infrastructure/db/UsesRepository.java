package com.example.holubsqlbackend.infrastructure.db;

import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class UsesRepository {
    HolubRepository holubRepository = new HolubRepository();
    public List<UsesEntity> getUses() {
        Table table = holubRepository.getTable("select * from uses");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<UsesEntity> useEntities = new LinkedList<>();
        for (Object o : rowSet) {
            Object[] rowData = (Object[]) o;
            useEntities.add(UsesEntity.builder()
                    .uses_id(rowData[0].toString())
                    .ingredient_id(rowData[1].toString())
                    .menu_id(rowData[2].toString())
                    .build());
        }
        return useEntities;
    }
}
