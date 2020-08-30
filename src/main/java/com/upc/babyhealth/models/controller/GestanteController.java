package com.upc.babyhealth.models.controller;

import java.util.List;

import com.upc.babyhealth.models.entity.Monitoreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.service.implementation.GestanteService;

import javax.ws.rs.GET;


@RestController
public class GestanteController {
	
	@Autowired
	private final GestanteService gestanteService;

	public GestanteController(GestanteService gestanteService) {
		
		this.gestanteService = gestanteService;
	}
	
	@GetMapping("/")
	public String helloworld() {
		return "Holis";
	}
	
	
	@GetMapping("/gestantes")
	List<Gestante> all(){
		return gestanteService.findAll();
	}
	
	@PostMapping("/gestantes")
	Gestante newGestante(@RequestBody Gestante newGestante) {
		return gestanteService.save(newGestante);
	}
	
	@GetMapping("/gestantes/{id}")
	Gestante one(@PathVariable Long id) {
		return gestanteService.findOne(id);
	}
	
	@PutMapping("/gestantes/{id}")
	Gestante replaceGestante(@RequestBody Gestante nuevaGestante, @PathVariable Long id) {

		//TODO
		return gestanteService.findOne(id);
				/*
				.map(gestante -> {
					gestante.setNombres(nuevaGestante.getNombres());
					gestante.setApellidoMaterno(nuevaGestante.getApellidoMaterno());
					gestante.setApellidoPaterno(nuevaGestante.getApellidoPaterno());
					gestante.setDni(nuevaGestante.getDni());
					gestante.setEdad(nuevaGestante.getEdad());
					gestante.setIndCompartirUbicacion(nuevaGestante.isIndCompartirUbicacion());
					gestante.setSemanaGestacional(nuevaGestante.getSemanaGestacional());
					return gestanteService.save(nuevaGestante);
				})
				.orElseGet(()-> {
					nuevaGestante.setId(id);
					return gestanteService.save(nuevaGestante);
				});

				 */
	}
	
	@DeleteMapping("/gestantes/{id}")
	void deleteGestante(@PathVariable Long id) {
		gestanteService.deleteById(id);
	}


}
