package com.upc.babyhealth.models.service.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upc.babyhealth.models.dao.CelularRepository;
import com.upc.babyhealth.models.entity.Celular;
import com.upc.babyhealth.models.entity.request.CelularPostRequest;
import com.upc.babyhealth.models.entity.request.CelularPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CelularService implements com.upc.babyhealth.models.service.CelularService {

    @Autowired
    private CelularRepository celularRepository;

    @Override
    public Celular insert(CelularPostRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Celular c = new Celular();
        c = mapper.convertValue(request, Celular.class);
        return celularRepository.save(c);
    }

    @Override
    public Celular update(Long id, CelularPutRequest request) {
        Celular existingCelular = celularRepository.findById(id).orElse(null);
        if(existingCelular != null){
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            existingCelular = mapper.convertValue(request,Celular.class);
            celularRepository.save(existingCelular);
        }
        return null;
    }

}
