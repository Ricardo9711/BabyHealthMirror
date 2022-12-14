package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Obstetra;

public interface ObstetraService {

    Obstetra findByDni(Long dni);
    Obstetra save(Obstetra obstetra);
    Obstetra findById(Long id);
    Obstetra findByUserId(Long id);
    Obstetra findByUsername(String username);
    Obstetra update(Obstetra obstetra, Long obstetraId);
}
