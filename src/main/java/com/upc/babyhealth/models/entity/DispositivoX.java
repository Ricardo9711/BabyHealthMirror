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
	private ZonedDateTime fechaTotal; //esto puede guardar fecha y hora
	private LocalDate fecha; // por definir
	private LocalTime hora; // por definir
	private String evento;  // por definir
	private Double valorRegistrado; //por definir
	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaMoificacion;
	

}
