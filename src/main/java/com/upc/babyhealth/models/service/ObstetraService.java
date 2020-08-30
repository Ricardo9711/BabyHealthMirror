package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Obstetra;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ObstetraService {

    Obstetra findByDni(Long dni);

    Obstetra save(Obstetra obstetra);

}
