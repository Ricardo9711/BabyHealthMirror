package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.TipoAlerta;

import java.util.List;

public interface TipoAlertaService {
    public TipoAlerta findByName(String name);
    List<TipoAlerta> findAll();
}
