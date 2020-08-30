package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.ObstetraRepository;
import com.upc.babyhealth.models.entity.Obstetra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObstetraService implements com.upc.babyhealth.models.service.ObstetraService {

    @Autowired
    private ObstetraRepository obstetraRepository;

    @Override
    public Obstetra findByDni(Long dni) {

        return obstetraRepository.findByDni(dni);
    }

    @Override
    public Obstetra save(Obstetra obstetra) {
        return obstetraRepository.save(obstetra);
    }
}
