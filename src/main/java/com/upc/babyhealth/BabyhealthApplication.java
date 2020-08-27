package com.upc.babyhealth;

import com.upc.babyhealth.models.dao.GestanteRepository;
import com.upc.babyhealth.models.dao.RolRepository;
import com.upc.babyhealth.models.dao.UsuarioRepository;
import com.upc.babyhealth.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


//(exclude={DataSourceAutoConfiguration.class})

@SpringBootApplication
public class BabyhealthApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private GestanteRepository gestanteRepository;

	/*
	@PostConstruct
	public void initUsers() {
		Rol rol = new Rol();
		rol.setIdRol((long) 1);
		rol.setNombreRol(RolEnum.GESTANTE);
		ArrayList<Usuario> usuarios = new ArrayList<>();

		Usuario usuario = new Usuario();
		usuario.setRol(rol);
		usuario.setNombreUsuario("juanelv");
		usuario.setContrasenia("123");

		usuarios.add(usuario);
		rol.setUsuarios(usuarios);
		rol.addUsuario(usuario);

		Gestante gestante = new Gestante();
		gestante.setNombres("Paula");
		gestante.setApellidoMaterno("Arroyo");
		gestante.setApellidoMaterno("Blanco");
		gestante.setEdad((long) 40);
		gestante.setDni((long) 43132211);
		gestante.setIndCompartirUbicacion(true);
		gestante.setSemanaGestacional((long) 3);
		gestante.setUsuario(usuario);

		CentroSalud centroSalud = new CentroSalud();
		centroSalud.setNombreCentroSalud("Puente al Cielo");
		centroSalud.setDireccion("Jr. Aqui nomas");
		centroSalud.setDistrito("Callao");
		centroSalud.setDepartamento("Callao");
		centroSalud.setProvincia("Callao");
		centroSalud.setFechaCreacion(ZonedDateTime.now());
		centroSalud.setLatitud((double) -10);
		centroSalud.setLongitud((double) -20);
		centroSalud.setFechaModificacion(null);
		centroSalud.setUsuarioCreacion("creador");
		centroSalud.setUsuarioCreacion(null);

		Usuario usuario2 = new Usuario();
		usuario2.setRol(rol);
		usuario2.setNombreUsuario("juanelvObstetra");
		usuario2.setContrasenia("123");
		usuarios.add(usuario2);
		Obstetra obstetra = new Obstetra();
		obstetra.setNombreObstetra("Carla");
		obstetra.setApellidoPaterno("Perez");
		obstetra.setApellidoMaterno("Gomez");
		obstetra.setDni((long) 749238);
		obstetra.setUsuario(usuario2);
		obstetra.setCentroSalud(centroSalud);


		gestante.setObstetra(obstetra);

		rolRepository.save(rol);
		usuarioRepository.save(usuario);
		//gestanteRepository.save(gestante);
		System.out.println("CONTRASEÑA cuack: " + usuario.getContrasenia());
	}
	 */

	public static void main(String[] args) {
		SpringApplication.run(BabyhealthApplication.class, args);
	}

}
