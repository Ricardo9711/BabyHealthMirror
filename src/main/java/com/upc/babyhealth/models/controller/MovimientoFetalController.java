package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.MovimientoFetal;
import com.upc.babyhealth.models.service.MovimientoFetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/monitoreos/{id}/movimiento-fetal")
    List<MovimientoFetal>findByMonitoreo(@PathVariable Long id){
        return movimientoFetalService.findByMonitoreoId(id);
    }

}
