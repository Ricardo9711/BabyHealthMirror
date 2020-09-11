package com.upc.babyhealth.models.service;

import java.util.List;
import java.util.Optional;

import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.entity.Monitoreo;

public interface GestanteService {
	
	public List<Gestante> findAll();
	public Gestante save(Gestante gestante);
	public Gestante findOne(Long id);
	public void deleteById(Long id);
	public Gestante findByDni (Long Dni);
	public Gestante findByUserId(Long id);
	public Gestante findByUsername(String username);


    Gestante update(Gestante nuevaGestante);
}
