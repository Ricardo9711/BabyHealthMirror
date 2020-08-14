package com.upc.babyhealth.models.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.service.GestanteService;


@RestController
public class GestanteController {
	
	@Autowired
	private final GestanteService gestanteService;

	public GestanteController(GestanteService gestanteService) {
		
		this.gestanteService = gestanteService;
	}
	
	@GetMapping("/gestantes")
	List<Gestante> all(){
		return gestanteService.findAll();
	}
	
	@PostMapping("/gestantes")
	Gestante newGestante(@RequestBody Gestante newGestante) {
		return gestanteService.saveRPC(newGestante); 
	}
	
	@GetMapping("/gestantes/{id}")
	Gestante one(@PathVariable Long id) {
		return gestanteService.findOne(id);
	}
	
	@PutMapping("/gestantes/{id}")
	Gestante replaceGestante(@RequestBody Gestante nuevaGestante, @PathVariable Long id) {
		
		return gestanteService.findOneRPC(id)
				.map(gestante -> {
					gestante.setNombres(nuevaGestante.getNombres());
					gestante.setApellidoMaterno(nuevaGestante.getApellidoMaterno());
					gestante.setApellidoPaterno(nuevaGestante.getApellidoPaterno());
					gestante.setDni(nuevaGestante.getDni());
					gestante.setEdad(nuevaGestante.getEdad());
					gestante.setIndCompartirUbicacion(nuevaGestante.getIsIndCompartirUbicacion());
					gestante.setRole(nuevaGestante.getRole());
					gestante.setSemanaGestacional(nuevaGestante.getSemanaGestacional());
					return gestanteService.saveRPC(nuevaGestante);
				})
				.orElseGet(()-> {
					nuevaGestante.setId(id);
					return gestanteService.saveRPC(nuevaGestante);
				});
	}
	
	@DeleteMapping("/gestantes/{id}")
	void deleteGestante(@PathVariable Long id) {
		gestanteService.deleteById(id);
	}
}
