package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.MovimientoFetal;

public interface MovimientoFetalService {

    MovimientoFetal findById(Long id);
    MovimientoFetal save(MovimientoFetal movimientoFetal);
    MovimientoFetal update(MovimientoFetal updatedMovimientoFetal);
}
