package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Celular {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idCelular;
	private Long numero;
	private Long imei;
	private String operador;
	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaMoificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private String estado;
}
