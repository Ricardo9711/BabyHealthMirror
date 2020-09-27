package com.upc.babyhealth.models.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upc.babyhealth.models.dao.ContraccionRepository;
import com.upc.babyhealth.models.entity.Contraccion;
import com.upc.babyhealth.models.entity.request.ContraccionRequest;
import com.upc.babyhealth.models.service.MonitoreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ContraccionService implements com.upc.babyhealth.models.service.ContraccionService {

    @Autowired
    private ContraccionRepository contraccionRepository;
    @Autowired
    private MonitoreoService monitoreoService;

    @Override
    public List<Contraccion> findAll() {
        return  contraccionRepository.findAll();
    }

    @Override
    public Contraccion findById(Long id) {
        return contraccionRepository.findById(id).orElse(null);
    }

    @Override
    public Contraccion save(ContraccionRequest contraccionRequest) throws Exception {
        Contraccion contraccion = null;

        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            contraccion = mapper.convertValue(contraccionRequest,Contraccion.class);
            contraccion.setMonitoreo( monitoreoService.findById(contraccionRequest.getMonitoreoId()) );
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return contraccionRepository.save(contraccion);
    }

    @Override
    public Contraccion update(ContraccionRequest contraccionRequest, Long id) {
        return null;
    }

    @Override
    public List<Contraccion> findByMonitoreoId(Long monitoreoId) {
        return contraccionRepository.findByMonitoreo_IdMonitoreo(monitoreoId);
    }
}
