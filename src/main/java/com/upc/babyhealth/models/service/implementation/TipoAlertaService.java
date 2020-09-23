package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.TipoAlertaRepository;
import com.upc.babyhealth.models.entity.TipoAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAlertaService implements com.upc.babyhealth.models.service.TipoAlertaService {

    @Autowired
    private TipoAlertaRepository tipoAlertaRepository;
    @Override
    public TipoAlerta findByName(String name) {
        return tipoAlertaRepository.findByNombre(name);
    }

    @Override
    public List<TipoAlerta> findAll() {
       return tipoAlertaRepository.findAllByIdTipoBetween(Long.valueOf(3), Long.valueOf(99999));
    }
}
