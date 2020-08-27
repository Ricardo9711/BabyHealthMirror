package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Contraccion;
import com.upc.babyhealth.models.service.implementation.ContraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
