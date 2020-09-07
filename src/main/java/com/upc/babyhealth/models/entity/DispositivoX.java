package com.upc.babyhealth.models.entity;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class DispositivoX {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRegistro;
	private Long idDispositivo;
	private ZonedDateTime fechaEvento; //hora y fecha de la captura del sensor
	private String evento; //default: autoreplay
	private Double valorRegistrado;
	private ZonedDateTime fechaCreacion;
	private String usuarioCreacion; //default: SENSOR

}
