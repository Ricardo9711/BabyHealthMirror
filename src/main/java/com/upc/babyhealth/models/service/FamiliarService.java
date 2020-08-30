package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Familiar;

import java.util.List;

public interface FamiliarService{
    
    public Familiar save(Familiar familiar, Long gestanteId);
    public List<Familiar> findAllByGestante(Long gestanteId);
}
