package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.domain.entity.UsesEntity;
import com.example.holubsqlbackend.infrastructure.db.UsesRepository;
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
public class UsesService {
    private final UsesRepository usesRepository;

    public List<UsesEntity> searchUsesByMenuId(String id) {
        List<UsesEntity> useEntities = usesRepository.getUses();
        List<UsesEntity> results = new LinkedList<>();
        for (UsesEntity useEntity : useEntities) {
            if (useEntity.getMenu_id().equals(id)) {
                results.add(useEntity);
            }
        }
        return results;
    }
}