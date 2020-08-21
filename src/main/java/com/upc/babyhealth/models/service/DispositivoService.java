package com.upc.babyhealth.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.babyhealth.models.dao.DispositivoRepository;

@Service
public class DispositivoService implements IDispositivoService {
	
	@Autowired
	private DispositivoRepository dispositivoRepo;
	
	
	

}
