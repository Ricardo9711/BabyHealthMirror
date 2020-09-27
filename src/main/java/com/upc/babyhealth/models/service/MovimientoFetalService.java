package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.MovimientoFetal;

import java.util.List;

public interface MovimientoFetalService {

    MovimientoFetal findById(Long id);
    MovimientoFetal save(MovimientoFetal movimientoFetal);
    MovimientoFetal update(MovimientoFetal updatedMovimientoFetal);
    List<MovimientoFetal> findByMonitoreoId(Long monitoreoId);
}
