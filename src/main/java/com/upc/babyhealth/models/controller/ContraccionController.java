package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Contraccion;
import com.upc.babyhealth.models.service.implementation.ContraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracciones")
public class ContraccionController {

    @Autowired
    ContraccionService contraccionService;

    @GetMapping("/")
    List<Contraccion> all(){
        return contraccionService.findAll();
    }

    @GetMapping("/{id}")
    Contraccion findById(@PathVariable Long id){
        return contraccionService.findById(id);
    }

    //TODO POST
    @PostMapping()
    Contraccion save(@RequestBody Contraccion contraccion){
        return contraccionService.save(contraccion);
    }

}
