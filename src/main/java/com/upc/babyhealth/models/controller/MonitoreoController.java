package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.service.MonitoreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;


    @GetMapping(value="/gestantes/{id}/monitoreos")
    public List<Monitoreo> findByGestanteIdAndSemana(@PathVariable Long id, @RequestParam int semana){
        return monitoreoService.findBySemanaAndGestante(semana, id);
    }

    @GetMapping("/gestantes/{id}/monitoreos/last")
    public Monitoreo findLastMonitoreoByGestanteId(@PathVariable Long id){
        return monitoreoService.findLastMonitoreo(id);
    }

}
