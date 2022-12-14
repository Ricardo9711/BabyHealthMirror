package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Celular;
import com.upc.babyhealth.models.entity.request.CelularPostRequest;
import com.upc.babyhealth.models.entity.request.CelularPutRequest;
import com.upc.babyhealth.models.service.CelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class CelularController {

    @Autowired
    private CelularService celularService;

    @PostMapping("/celulares")
    Celular insert(@RequestBody CelularPostRequest celularRequest) {
        return celularService.insert(celularRequest);
    }

    @PutMapping("/celulares/{id}")
    Celular update(@PathVariable Long id, @RequestBody CelularPutRequest celularRequest) {
        return celularService.update(id, celularRequest);
    }

    @GetMapping("/usuarios/{id}/celulares")
    List<Celular> find(@PathVariable Long id){
        return celularService.findByUsuarioId(id);
    }
}
