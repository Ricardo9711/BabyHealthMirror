package com.upc.babyhealth.models.service.implementation;

import java.util.List;
import java.util.Optional;

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
		// TODO Auto-generated method stub
		return gestanteRepo.findAll();
	}

	@Override
	public Gestante save(Gestante gestante) {
		// TODO Auto-generated method stub

		//verificar si es que el usuario existe

		return gestanteRepo.save(gestante);
	}

	@Override
	public Gestante findOne(Long id) {
		// TODO Auto-generated method stub
		return gestanteRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		gestanteRepo.deleteById(id);
	}

	@Override
	public Optional<Gestante> findByDni(Long dni) {
		// TODO Auto-generated method stub
		return gestanteRepo.findByDni(dni) ;
	}

	
	//rpc
	@Override
	public Gestante saveRPC(Gestante gestante) {
		// TODO Auto-generated method stub
		return gestanteRepo.save(gestante);
	}

	@Override
	public Optional<Gestante> findOneRPC(Long id) {
		// TODO Auto-generated method stub
		return gestanteRepo.findById(id);
	}

}
