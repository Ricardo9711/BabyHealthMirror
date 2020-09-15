package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.MovimientoFetal;
import com.upc.babyhealth.models.service.MovimientoFetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovimientoFetalController {

    @Autowired
    private MovimientoFetalService movimientoFetalService;

    @GetMapping("movimiento-fetal/{id}")
    MovimientoFetal findById(Long id){
        return movimientoFetalService.findById(id);
    }

    @PostMapping("/movimiento-fetal")
    MovimientoFetal save(@RequestBody MovimientoFetal movimientoFetal){
        return movimientoFetalService.save(movimientoFetal);
    }


}
