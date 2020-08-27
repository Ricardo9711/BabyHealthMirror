package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.ContraccionRepository;
import com.upc.babyhealth.models.entity.Contraccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContraccionService implements com.upc.babyhealth.models.service.ContraccionService {

    @Autowired
    private ContraccionRepository contraccionRepository;

    @Override
    public List<Contraccion> findAll() {
        return  contraccionRepository.findAll();
    }

    @Override
    public Contraccion findById(Long id) {
        return contraccionRepository.findById(id).orElse(null);
    }
}
