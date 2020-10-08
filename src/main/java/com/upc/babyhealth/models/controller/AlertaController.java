package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Alerta;
import com.upc.babyhealth.models.entity.request.AlertaRequest;
import com.upc.babyhealth.models.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping("/alertas")
    public Alerta sendAlert(@RequestBody AlertaRequest alerta){
        return alertaService.sendAlert(alerta);
    }

    @PutMapping("/alertas/{id}/visualizacion")
    public Alerta update(@PathVariable Long id){
        return alertaService.seeAlert(id);
    }

    //TODO PUT
}
