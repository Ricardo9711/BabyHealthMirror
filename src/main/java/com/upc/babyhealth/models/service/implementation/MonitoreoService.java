package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.MonitoreoRepository;
import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.entity.MonitoreoEstadoEnum;
import com.upc.babyhealth.models.entity.Obstetra;
import com.upc.babyhealth.models.entity.request.MonitoreoPutRequest;
import com.upc.babyhealth.models.entity.request.MonitoreoRequest;
import com.upc.babyhealth.models.service.GestanteService;
import com.upc.babyhealth.models.service.ObstetraService;
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
    @Autowired
    private PushNotificationService pushNotificationService;
    @Autowired
    private ObstetraService obstetraService;

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
        //TODO EXCEPTION
        //if(gestante==null)
            //return new Exception();
        Monitoreo monitoreo = new Monitoreo();
        monitoreo.setGestante(gestante);
        monitoreo.setFechaCreacion(ZonedDateTime.now());
        monitoreo.setFechaInicio(monitoreo.getFechaCreacion());
        monitoreo.setEstado(MonitoreoEstadoEnum.I);
        monitoreo.setUsuarioCreacion(monitoreoRequest.getUsuarioCreacion());

        return monitoreRepository.save(monitoreo);
    }

    @Override
    public Monitoreo update(MonitoreoPutRequest monitoreoRequest, Long gestanteId, Long monitoreoId) {
        Monitoreo existingMonitoreo = monitoreRepository.findById(monitoreoId).orElse(null);
        if(existingMonitoreo != null){
            if(monitoreoRequest.getContraccionesPromedio() != null && !monitoreoRequest.getContraccionesPromedio().toString().equals(""))
                existingMonitoreo.setContraccionesPromedio(monitoreoRequest.getContraccionesPromedio());
            if(monitoreoRequest.getFrecuenciaPromedio() != null && !monitoreoRequest.getFrecuenciaPromedio().toString().equals(""))
                existingMonitoreo.setFrecuenciaPromedio(monitoreoRequest.getFrecuenciaPromedio());
            if(monitoreoRequest.getTiempoEcPromedio() != null && !monitoreoRequest.getTiempoEcPromedio().toString().equals(""))
                existingMonitoreo.setTiempoEcPromedio(monitoreoRequest.getTiempoEcPromedio());
            if(monitoreoRequest.getIntensidadPromedio() != null && !monitoreoRequest.getIntensidadPromedio().toString().equals(""))
                existingMonitoreo.setIntensidadPromedio(monitoreoRequest.getIntensidadPromedio());
            if(monitoreoRequest.getFechaModificacion() != null && !monitoreoRequest.getFechaModificacion().toString().equals(""))
                existingMonitoreo.setFechaModificacion(monitoreoRequest.getFechaModificacion());
            if(monitoreoRequest.getUsuarioModificacion() != null && !monitoreoRequest.getUsuarioModificacion().equals(""))
                existingMonitoreo.setUsuarioModificacion(monitoreoRequest.getUsuarioModificacion());
            if(monitoreoRequest.getFechaFin() != null && !monitoreoRequest.getFechaFin().toString().equals(""))
                if(existingMonitoreo.getFechaFin() == null){
                    //ya ha terminado el monitoreo
                    Gestante g = gestanteService.findOne(gestanteId);
                    Obstetra o = obstetraService.findById(g.getObstetra().getId());
                    //TODO NOTIFICATION
                    //pushNotificationService.notifyFinishedMonitoring(o.getToken());
                }
                existingMonitoreo.setFechaFin(monitoreoRequest.getFechaFin());

            return monitoreRepository.save(existingMonitoreo);
        }
        return null;
    }
}
