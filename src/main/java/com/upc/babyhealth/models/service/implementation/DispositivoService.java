package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.entity.Dispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.babyhealth.models.dao.DispositivoRepository;

import java.util.List;

@Service
public class DispositivoService implements com.upc.babyhealth.models.service.DispositivoService {
	@Autowired
	private DispositivoRepository dispositivoRepo;

	@Override
	public List<Dispositivo> findAll() {
		return null;
	}

	@Override
	public void save(Dispositivo dispositivo) {

	}
}
