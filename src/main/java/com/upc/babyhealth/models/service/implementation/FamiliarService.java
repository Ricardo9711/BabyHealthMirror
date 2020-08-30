package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.FamiliarRepository;
import com.upc.babyhealth.models.entity.Familiar;
import com.upc.babyhealth.models.entity.Gestante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamiliarService implements com.upc.babyhealth.models.service.FamiliarService {

    @Autowired
    private FamiliarRepository familiarRepository;

    @Override
    public Familiar save(Familiar familiar, Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        familiar.setGestante(gestante);
        return familiarRepository.save(familiar);
    }

    @Override
    public List<Familiar> findAllByGestante(Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return familiarRepository.findByGestante(gestante);
    }
}
