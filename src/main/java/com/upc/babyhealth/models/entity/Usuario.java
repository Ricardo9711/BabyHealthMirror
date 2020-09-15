package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contrasenia"})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(nullable = false)
	private String nombreUsuario;

	@Column(nullable = false)
	private String contrasenia;

	@Enumerated(EnumType.STRING)
	private UsuarioEstadoEnum estado;

	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private String email;
	private String nroCelular;


	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRol")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JoinColumn(name="FK_ROL", nullable=false)
	private Rol rol;


}
