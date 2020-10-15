package com.upc.babyhealth.models.service.implementation;

import java.util.List;
import java.util.Optional;

import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.entity.Obstetra;
import com.upc.babyhealth.models.entity.Usuario;
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
		gestante.setPatronMovimientos(0);
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
	public Gestante findByUserId(Long id) {
		Usuario u = new Usuario();
		u.setIdUsuario(id);
		return gestanteRepo.findByUsuario(u);
	}

	@Override
	public Gestante findByUsername(String username) {
		return gestanteRepo.findByUsuario_NombreUsuario(username);
	}

	@Override
	public List<Gestante> findAllByObstetra(Long obstetraId) {
		return gestanteRepo.findGestanteByObstetra_Id(obstetraId);
	}

	@Override
	public Gestante update(Gestante nuevaGestante) {
		//TODO
		return gestanteRepo.save(nuevaGestante);
	}

	public Gestante estabilizarGestante(Long gestanteId){

		Gestante g = gestanteRepo.findById(gestanteId).orElse(null);
		if(g != null){
			g.setEstado("ESTABLE");
			g.setOrigenEstado("MANUAL");
			g = gestanteRepo.save(g);
		}
		return g;
	}




}
