package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Familiar;
import com.upc.babyhealth.models.service.FamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamiliarController {

    @Autowired
    FamiliarService familiarService;

    @PostMapping("/gestantes/{id}/familiares")
    Familiar save(@PathVariable Long id, @RequestBody  Familiar newFamiliar){
        return familiarService.save(newFamiliar, id);
    }

    @GetMapping("/gestantes/{id}/familiares")
    List<Familiar> findAllByGestante(@PathVariable Long id){
        return familiarService.findAllByGestante(id);
    }

    @PutMapping("/gestantes/{gestanteId}/familiares/{familiarId}")
    Familiar update(@PathVariable Long gestanteId,@PathVariable Long familiarId, @RequestBody Familiar modifiedFamiliar){
        return familiarService.update(gestanteId,familiarId,modifiedFamiliar);
    }

    @DeleteMapping("/gestantes/{gestanteId}/familiares/{familiarId}")
    void delete(@PathVariable Long gestanteId,@PathVariable Long familiarId){
        familiarService.delete(gestanteId, familiarId);
    }

}
