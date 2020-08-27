package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Contraccion;

import java.util.List;

public interface ContraccionService {

    public List<Contraccion> findAll();
    public Contraccion findById(Long id);
}
