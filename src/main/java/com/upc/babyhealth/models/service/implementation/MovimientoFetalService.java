package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.MovimientoFetalRepository;
import com.upc.babyhealth.models.entity.MovimientoFetal;
import com.upc.babyhealth.models.entity.request.MonitoreoPutRequest;
import com.upc.babyhealth.models.service.MonitoreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoFetalService implements com.upc.babyhealth.models.service.MovimientoFetalService {

    @Autowired
    private MovimientoFetalRepository movimientoFetalRepository;
    @Autowired
    private MonitoreoService monitoreoService;

    @Override
    public MovimientoFetal findById(Long id) {
        return movimientoFetalRepository.findById(id).orElse(null);
    }

    @Override
    public MovimientoFetal save(MovimientoFetal movimientoFetal) {
        MovimientoFetal mf = movimientoFetalRepository.save(movimientoFetal);
        if(mf != null) {
            monitoreoService.addMovFetal(mf.getMonitoreo().getIdMonitoreo());
        }
        return mf;
    }

    @Override
    public MovimientoFetal update(MovimientoFetal updatedMovimientoFetal) {
        //TODO
        return null;
    }

    @Override
    public List<MovimientoFetal> findByMonitoreoId(Long monitoreoId) {
        return movimientoFetalRepository.findByMonitoreo_IdMonitoreo(monitoreoId);
    }
}
