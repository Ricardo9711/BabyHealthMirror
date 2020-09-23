package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.MonitoreoRepository;
import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.entity.request.MonitoreoRequest;
import com.upc.babyhealth.models.service.GestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class MonitoreoService implements com.upc.babyhealth.models.service.MonitoreoService {

    @Autowired
    private MonitoreoRepository monitoreRepository;
    @Autowired
    private GestanteService gestanteService;

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
    public Monitoreo findById(Long monitoreoId) {
        return monitoreRepository.findById(monitoreoId).orElse(null);
    }


    @Override
    public Monitoreo save(MonitoreoRequest monitoreoRequest, Long gestanteId) {
        Gestante gestante = gestanteService.findOne(gestanteId);
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setGestante(gestante);
        monitoreo.setFechaCreacion(ZonedDateTime.now());
        monitoreo.setFechaInicio(monitoreo.getFechaCreacion());
        monitoreo.setUsuarioCreacion(monitoreoRequest.getUsuarioCreacion());

        return monitoreRepository.save(monitoreo);
    }

    @Override
    public Monitoreo update(MonitoreoRequest monitoreoRequest, Long gestanteId, Long monitoreoId) {
        return null;
    }
}
