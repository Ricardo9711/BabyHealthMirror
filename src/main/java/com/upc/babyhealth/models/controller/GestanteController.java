package com.upc.babyhealth.models.controller;

import java.util.List;

import com.upc.babyhealth.models.entity.DispositivoGestante;
import com.upc.babyhealth.models.entity.Monitoreo;
import com.upc.babyhealth.models.service.DispositivoGestanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.service.implementation.GestanteService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
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

		return gestanteService.update(nuevaGestante);
	}
	
	@DeleteMapping("/gestantes/{id}")
	void deleteGestante(@PathVariable Long id) {
		gestanteService.deleteById(id);
	}

	@GetMapping("/obstetras/{id}/gestantes")
	List<Gestante> findByObstetra(@PathVariable Long id){
		return gestanteService.findAllByObstetra(id);
	}

	@PostMapping("/gestantes/{id}/estado")
	Gestante cambiarEstado(@PathVariable Long id){
		return gestanteService.estabilizarGestante(id);
	}


}
