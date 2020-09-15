package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.MovimientoFetalRepository;
import com.upc.babyhealth.models.entity.MovimientoFetal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoFetalService implements com.upc.babyhealth.models.service.MovimientoFetalService {

    @Autowired
    private MovimientoFetalRepository movimientoFetalRepository;

    @Override
    public MovimientoFetal findById(Long id) {
        return movimientoFetalRepository.findById(id).orElse(null);
    }

    @Override
    public MovimientoFetal save(MovimientoFetal movimientoFetal) {
        return movimientoFetalRepository.save(movimientoFetal);
    }

    @Override
    public MovimientoFetal update(MovimientoFetal updatedMovimientoFetal) {
        //TODO
        return null;
    }
}
