package com.upc.babyhealth.models.service;

import java.util.List;
import java.util.Optional;

import com.upc.babyhealth.models.entity.Gestante;

public interface IGestanteService {
	
	public List<Gestante> findAll();
	public void save(Gestante gestante);
	public Gestante findOne(Long id);
	public void deleteById(Long id);
	public Gestante findByDni (Long Dni);
	
	
	//RPC Service
	public Gestante saveRPC(Gestante gestante);
	public Optional<Gestante> findOneRPC(Long id);
}
