package com.example.holubsqlbackend.domain.service;

import com.example.holubsqlbackend.application.dto.RtDTO;

import java.util.List;

public abstract class TemplateRtFilter {
    List<RtDTO> rtEntities;
    void setRtList(List<RtDTO> rtEntities){
        this.rtEntities = rtEntities;
    }

    abstract void filterRtList(Object filter);

    List<RtDTO> getFilteredResult(){
        return rtEntities;
    }
}
