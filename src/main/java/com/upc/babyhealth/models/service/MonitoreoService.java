package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.entity.request.MonitoreoPutRequest;
import com.upc.babyhealth.models.entity.request.MonitoreoRequest;

import java.util.List;

public interface MonitoreoService {
    public List<Monitoreo> findBySemanaAndGestante(Integer semana, Long gestanteId);
    public List<Monitoreo> findByGestante(Long gestanteId);
    public Monitoreo findLastMonitoreo(Long gestanteId);
    public Monitoreo findById(Long monitoreoId);
    Monitoreo save(MonitoreoRequest monitoreoRequest, Long gestanteId);
    Monitoreo update(MonitoreoPutRequest monitoreoRequest, Long gestanteId, Long monitoreoId);
}
