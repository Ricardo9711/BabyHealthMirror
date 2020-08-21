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
public class TipoContraccion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idTipoContraccion;
	private String descripcionTipo;
	private Double desdeMmg;  //por definir
	private Double hastaMmg;  //por definir
	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;

}
