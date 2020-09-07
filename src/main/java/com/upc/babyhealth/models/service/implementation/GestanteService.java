package com.upc.babyhealth.models.service.implementation;

import java.util.List;
import java.util.Optional;

import com.upc.babyhealth.models.entity.Monitoreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.upc.babyhealth.models.dao.GestanteRepository;
import com.upc.babyhealth.models.entity.Gestante;


@Service
public class GestanteService implements com.upc.babyhealth.models.service.GestanteService {
	
	@Autowired
	private GestanteRepository gestanteRepo;
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public List<Gestante> findAll() {
		return gestanteRepo.findAll();
	}

	@Override
	public Gestante save(Gestante gestante) {

		//verificar si es que el usuario existe

		return gestanteRepo.save(gestante);
	}

	@Override
	public Gestante findOne(Long id) {
		return gestanteRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		gestanteRepo.deleteById(id);
	}

	@Override
	public Gestante findByDni(Long dni) {
		return gestanteRepo.findByDni(dni) ;
	}

	@Override
	public Gestante update(Gestante nuevaGestante) {
		//TODO
		return gestanteRepo.save(nuevaGestante);
	}


}
