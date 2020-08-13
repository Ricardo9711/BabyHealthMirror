package com.upc.babyhealth.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.upc.babyhealth.models.dao.GestanteRepository;
import com.upc.babyhealth.models.entity.Gestante;

@Service
public class GestanteService implements IGestanteService{
	
	private GestanteRepository gestanteRepo ;

	@Override
	public List<Gestante> findAll() {
		// TODO Auto-generated method stub
		return gestanteRepo.findAll();
	}

	@Override
	public void save(Gestante gestante) {
		// TODO Auto-generated method stub
		gestanteRepo.save(gestante);
	}

	@Override
	public Gestante findOne(Long id) {
		// TODO Auto-generated method stub
		return gestanteRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		gestanteRepo.deleteById(id);
	}

	@Override
	public Gestante findByDni(Long dni) {
		// TODO Auto-generated method stub
		return gestanteRepo.findByDni(dni).get(0);
	}

}
