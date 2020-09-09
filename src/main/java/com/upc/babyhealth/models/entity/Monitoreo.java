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
public class Monitoreo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idMonitoreo;
	private ZonedDateTime fechaInicio;
	private ZonedDateTime fechaFin;
	//TODO
	@Enumerated(EnumType.STRING)
	private MonitoreoEstadoEnum estado;
	private Double contraccionesPromedio;
	private Double frecuenciaPromedio;
	private Double tiempoEcPromedio;
	private Double intensidadPromedio;
	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Integer semanaGestacion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="FK_GESTANTE", nullable=false)
	private Gestante gestante;


}
