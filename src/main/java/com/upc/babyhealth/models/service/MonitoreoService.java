package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Monitoreo;

import java.util.List;

public interface MonitoreoService {
    public List<Monitoreo> findBySemanaAndGestante(int semana, Long gestanteId);
    public Monitoreo findLastMonitoreo(Long gestanteId);
    Monitoreo save(Monitoreo monitoreo);
}
