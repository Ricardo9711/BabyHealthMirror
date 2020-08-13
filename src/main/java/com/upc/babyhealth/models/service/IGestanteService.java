package com.upc.babyhealth.models.service;

import java.util.List;

import com.upc.babyhealth.models.entity.Gestante;

public interface IGestanteService {
	
	public List<Gestante> findAll();
	public void save(Gestante gestante);
	public Gestante findOne(Long id);
	public void delete(Long id);
	public Gestante findByDni (Long Dni);
	
}
