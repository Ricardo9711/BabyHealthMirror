package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Alerta;
import com.upc.babyhealth.models.entity.request.AlertaRequest;

public interface AlertaService {
    public Alerta sendAlert(AlertaRequest alerta);
    public Alerta seeAlert(Long id);
}
