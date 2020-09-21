package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.TipoAlerta;

public interface TipoAlertaService {
    public TipoAlerta findByName(String name);
}
