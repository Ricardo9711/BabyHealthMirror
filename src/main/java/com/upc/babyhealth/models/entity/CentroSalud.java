package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "obstetras"})

public class CentroSalud {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCentroSalud;
	private String nombreCentroSalud;
	private String direccion;
	private String departamento;
	private String provincia;
	private String distrito;
	private Double latitud;
	private Double longitud;
	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;

	@OneToMany(mappedBy="centroSalud",fetch=FetchType.LAZY)
	private List<Obstetra> obstetras;

	public void addObstetra(Obstetra obstetra){
		obstetras.add(obstetra);
	}

}
