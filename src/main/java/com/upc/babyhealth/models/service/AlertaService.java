package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Alerta;
import com.upc.babyhealth.models.entity.request.AlertaRequest;

import java.util.List;

public interface AlertaService {
    public Alerta sendAlert(AlertaRequest alerta);
    public Alerta seeAlert(Long id);
    public Alerta findTopByGestanteAndFechaVistoIsNullOrderByFechaCreacionDesc(Long idGestante);
    public List<Alerta> findAlertasByGestanteId(Long idGestante);
}
