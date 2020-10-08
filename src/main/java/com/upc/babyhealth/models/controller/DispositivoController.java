package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Dispositivo;
import com.upc.babyhealth.models.entity.DispositivoX;
import com.upc.babyhealth.models.entity.request.CapturaPostRequest;
import com.upc.babyhealth.models.service.DispositivoService;
import com.upc.babyhealth.models.service.DispositivoXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;
    @Autowired
    private DispositivoXService dispositivoXService;

    @GetMapping("/dispositivos/{id}")
    public Dispositivo findOne(@PathVariable Long id){
        return dispositivoService.findById(id);
    }

    @PostMapping("/dispositivos")
    public ResponseEntity save(@RequestBody Dispositivo dispositivo){
        return dispositivoService.save(dispositivo);
    }

    @PutMapping("/dispositivos")
    public Dispositivo update(@RequestBody Dispositivo dispositivo){
        return dispositivoService.update(dispositivo);
    }

    @GetMapping("/dispositivos/{dispositivoId}/capturas")
    public List<DispositivoX> findByIdAndDate(@PathVariable Long dispositivoId, @RequestParam String fecha, @RequestParam String hora){

        //ZonedDateTime dateFormatted = ZonedDateTime.parse(fecha + 'T' + hora +"Z[GMT]");
        return dispositivoXService.findByIdAndDate(dispositivoId, fecha, hora);
    }


    @PostMapping("/dispositivos/{dispositivoId}/capturas")
    public boolean sendCaptures(@PathVariable Long dispositivoId, @RequestBody List<CapturaPostRequest>capturas) {
        return dispositivoXService.saveCaptures(capturas, dispositivoId);
    }
}
