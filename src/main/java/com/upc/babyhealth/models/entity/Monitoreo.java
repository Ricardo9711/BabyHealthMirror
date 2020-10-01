package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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
	@Enumerated(EnumType.STRING)
	private MonitoreoEstadoEnum estado;
	private Double duracionPromedio;	//cuantas contracciones hay en 10 minutos. 10 min es el maximo de un monitoreo
	private Double frecuenciaPromedio; //frecuencia va a ser igual a contraccionesPromedio
	private Double tiempoEcPromedio; //Espacio de tiempo entre el fin de una contraccion y el inicio del otro
	private Double intensidadPromedio; //Intensidad promedio: 0 si es manual y otro
	private ZonedDateTime fechaCreacion; //igual a la fecha inicio
	private ZonedDateTime fechaModificacion; //va a ser igual a la fecha fin
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Integer semanaGestacion; //semana de gestacion del monitoreo
	private Integer cantidadMovFetales;
	private Integer cantidadContracciones;
	private String estadoGestante;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="FK_GESTANTE", nullable=false)
	private Gestante gestante;


}
