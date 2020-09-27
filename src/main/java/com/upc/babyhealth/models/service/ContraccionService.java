package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Contraccion;
import com.upc.babyhealth.models.entity.request.ContraccionRequest;

import java.util.List;

public interface ContraccionService {

    public List<Contraccion> findAll();
    public Contraccion findById(Long id);
    public Contraccion save(ContraccionRequest contraccionRequest) throws Exception;
    public Contraccion update(ContraccionRequest contraccionRequest, Long id);
    public List<Contraccion> findByMonitoreoId(Long monitoreoId);
}
