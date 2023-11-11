package com.example.holubsqlbackend.domain.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NameEntity {
    private String first;
    private String last;
    private String addrId;

    @Builder
    public NameEntity(String first, String last, String addrId) {
        this.first = first;
        this.last = last;
        this.addrId = addrId;
    }
}
