package com.upc.babyhealth.models.service;

import java.util.List;

import com.upc.babyhealth.models.entity.Dispositivo;

public interface DispositivoService {
	
	public List<Dispositivo> findAll();
	public Dispositivo save (Dispositivo dispositivo);
	public Dispositivo findById(Long id);
	public Dispositivo update(Dispositivo dispositivo);
}
