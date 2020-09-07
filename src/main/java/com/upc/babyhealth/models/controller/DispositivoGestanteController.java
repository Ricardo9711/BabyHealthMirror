package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.DispositivoGestante;
import com.upc.babyhealth.models.service.DispositivoGestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DispositivoGestanteController {
    @Autowired
    DispositivoGestanteService dispositivoGestanteService;

    @GetMapping("gestantes/{gestanteId}/dispositivos/{dispositivoId}")
    List<DispositivoGestante> findByGestanteIdAndDispositivoId(@PathVariable Long gestanteId, @PathVariable Long dispositivoId){
        return dispositivoGestanteService.findByGestanteIdAndDispositivoId(gestanteId,dispositivoId);
    }

    @GetMapping("dispositivos/{dispositivoId}/gestantes")
    List<DispositivoGestante> findByDispositivoId(@PathVariable Long dispositivoId){
        return dispositivoGestanteService.findByDispositivoId(dispositivoId);
    }

    @GetMapping("gestantes/{gestanteId}/dispositivos")
    List<DispositivoGestante> findByGestanteId(@PathVariable Long gestanteId){
        return dispositivoGestanteService.findByGestanteId(gestanteId);
    }

    //DispositivoGestante
    @PostMapping("gestantes/{id}/dispositivos")
    DispositivoGestante save(@RequestBody DispositivoGestante dispositivoAsignado){
        return dispositivoGestanteService.save(dispositivoAsignado);
    }

}
