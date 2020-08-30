package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;

import javax.persistence.*;

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
@ToString
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


	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name="FK_ROL", nullable=false)
	private Rol rol;


}
