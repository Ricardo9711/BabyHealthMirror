package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Alerta;
import com.upc.babyhealth.models.entity.request.AlertaRequest;
import com.upc.babyhealth.models.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping("/alertas")
    public Alerta sendAlert(@RequestBody AlertaRequest alerta){
        return alertaService.sendAlert(alerta);
    }

    //TODO PUT
}
