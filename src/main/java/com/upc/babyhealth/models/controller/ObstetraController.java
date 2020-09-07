package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Obstetra;
import com.upc.babyhealth.models.service.ObstetraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ObstetraController {

    @Autowired
    private ObstetraService obstetraService;

    @GetMapping("/obstetras/{id}")
    Obstetra findById(@PathVariable Long id){
        return obstetraService.findById(id);
    }

    @PostMapping("/obstetras")
    Obstetra save(@RequestBody Obstetra obstetra){
        return obstetraService.save(obstetra);
    }

    @PutMapping("/obstetras/{obstetraId}")
    Obstetra update(@RequestBody Obstetra obstetra, @PathVariable Long obstetraId ){
        return obstetraService.update(obstetra, obstetraId);
    }

}
