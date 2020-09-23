package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.TipoAlerta;
import com.upc.babyhealth.models.service.TipoAlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TipoAlertaController {
    @Autowired
    private TipoAlertaService tipoAlertaService;


    @GetMapping("/tipo-alertas")
    List<TipoAlerta> findAll(){
        return tipoAlertaService.findAll();
    }
}
