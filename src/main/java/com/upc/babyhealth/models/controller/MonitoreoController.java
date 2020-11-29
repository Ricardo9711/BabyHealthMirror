package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.entity.request.MonitoreoPutRequest;
import com.upc.babyhealth.models.entity.request.MonitoreoRequest;
import com.upc.babyhealth.models.service.MonitoreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class MonitoreoController {

    @Autowired
    private MonitoreoService monitoreoService;

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
        Monitoreo m = monitoreoService.findLastMonitoreo(id);
        return m;
    }

    @PostMapping("/gestantes/{id}/monitoreos")
    public Monitoreo save(@RequestBody MonitoreoRequest monitoreoRequest, @PathVariable Long id){
        return monitoreoService.save(monitoreoRequest, id);
    }

    //finalizacion de monitoreo
    @PutMapping("/gestantes/{gestanteId}/monitoreos/{monitoreoId}")
    public Monitoreo update(@RequestBody MonitoreoPutRequest monitoreoRequest, @PathVariable Long gestanteId, @PathVariable Long monitoreoId){
        return monitoreoService.update(monitoreoRequest, gestanteId,monitoreoId);
    }

}
