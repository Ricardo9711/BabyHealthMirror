package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.MonitoreRepository;
import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.entity.Monitoreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoreoService implements com.upc.babyhealth.models.service.MonitoreoService {

    @Autowired
    private MonitoreRepository monitoreRepository;

    @Override
    public List<Monitoreo> findBySemanaAndGestante(Integer semana, Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return monitoreRepository.findBySemanaGestacionAndGestante(semana, gestante);
    }

    @Override
    public List<Monitoreo> findByGestante(Long gestanteId) {
        return monitoreRepository.findByGestante_Id(gestanteId);
    }

    @Override
    public Monitoreo findLastMonitoreo(Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return monitoreRepository.findTopByGestanteOrderByFechaCreacionDesc(gestante);
    }

    @Override
    public Monitoreo save(Monitoreo monitoreo) {
        return monitoreRepository.save(monitoreo);
    }
}
