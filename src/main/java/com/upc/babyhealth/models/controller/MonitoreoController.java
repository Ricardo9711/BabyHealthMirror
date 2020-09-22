package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.entity.request.MonitoreoRequest;
import com.upc.babyhealth.models.service.MonitoreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;


    //TODO
    /*
    @GetMapping(value="/gestantes/{id}/monitoreos")
    public List<Monitoreo> findByGestanteId(@PathVariable Long id){
        return monitoreoService.findByGestante(id);
    }
    */

    @GetMapping(value="/gestantes/{id}/monitoreos")
    public List<Monitoreo> findByGestanteIdAndSemana(@PathVariable Long id, @RequestParam(required=false) Integer semana){
        if(semana != null) {
            return monitoreoService.findBySemanaAndGestante(semana, id);
        }
        else{
            return monitoreoService.findByGestante(id);
        }
    }

    @GetMapping("/gestantes/{id}/monitoreos/last")
    public Monitoreo findLastMonitoreoByGestanteId(@PathVariable Long id){
        return monitoreoService.findLastMonitoreo(id);
    }

    @PostMapping("/gestantes/{id}/monitoreos")
    public Monitoreo save(@RequestBody MonitoreoRequest monitoreoRequest, @PathVariable Long id){
        return monitoreoService.save(monitoreoRequest, id);
    }

}
