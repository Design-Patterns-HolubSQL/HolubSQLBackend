package com.example.holubsqlbackend.infrastructure.db;

import com.example.holubsqlbackend.domain.entity.IngredientEntity;
import com.example.holubsqlbackend.infrastructure.db.holub.HolubRepository;
import com.example.holubsqlbackend.infrastructure.db.holub.database.ConcreteTable;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.database.UnmodifiableTable;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class IngredientRepository {
    HolubRepository holubRepository = new HolubRepository();
    public List<IngredientEntity> getIngredients() {
        Table table = holubRepository.getTable("select * from ingredient");
        UnmodifiableTable u_t = (UnmodifiableTable) table;
        ConcreteTable c_t = (ConcreteTable) (u_t.extract());
        LinkedList rowSet = c_t.getRowSet();
        List<IngredientEntity> ingredientEntities = new LinkedList<>();
        for (Object o : rowSet) {
            Object[] rowData = (Object[]) o;
            ingredientEntities.add(IngredientEntity.builder()
                    .ingredient_id(rowData[0].toString())
                    .ingredient_name(rowData[1].toString())
                    .allergic(rowData[2].toString().equals("true"))
                    .build());
        }
        return ingredientEntities;
    }
}
