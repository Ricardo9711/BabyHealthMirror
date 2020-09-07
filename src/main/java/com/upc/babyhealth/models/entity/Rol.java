package com.upc.babyhealth.models.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "usuarios"})
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idRol;

	@Enumerated(EnumType.STRING)
	private RolEnum nombreRol;

	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;


	@OneToMany( mappedBy="rol",fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

	public void addUsuario(Usuario usuario){
		usuarios.add(usuario);
	}

}
