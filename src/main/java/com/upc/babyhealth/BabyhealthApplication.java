package com.upc.babyhealth;

import com.upc.babyhealth.models.dao.GestanteRepository;
import com.upc.babyhealth.models.dao.RolRepository;
import com.upc.babyhealth.models.dao.UsuarioRepository;
import com.upc.babyhealth.models.entity.*;
import com.upc.babyhealth.models.entity.request.SignUpRequest;
import com.upc.babyhealth.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@SpringBootApplication
public class BabyhealthApplication {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RolRepository rolRepository;

	@PostConstruct
	public void initAdmin(){

		//creacion de roles
		for (RolEnum r: RolEnum.values()){
			Rol rol = new Rol();
			rol.setNombreRol(r);
			rol.setFechaCreacion(ZonedDateTime.now(ZoneId.of("America/Lima")));
			rol.setUsuarioCreacion("MASTER");
			if( rolRepository.findByNombreRol(rol.getNombreRol()) == null)
				rolRepository.save(rol);
		}

		//creacion de usuario
		Usuario newUser = new Usuario();
		newUser.setNombreUsuario("MASTER");
		newUser.setContrasenia("Babyhealth@upc@2020");
		newUser.setUsuarioCreacion("MASTER");

		SignUpRequest signUpRequest = new SignUpRequest(
				newUser,
				null,
				"ADMINISTRADOR",
				"MASTER"
		);

		try {
			usuarioService.registerUser(signUpRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}



	public static void main(String[] args) {
		SpringApplication.run(BabyhealthApplication.class, args);
	}

}
