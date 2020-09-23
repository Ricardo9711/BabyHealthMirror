package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
@ToString
@Table(name="CONTRACCIONES")
public class Contraccion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_CONTRACCION")
	private Long idContraccion;
	@Column(name ="INTENSIDAD")
	private Double intensidad; //valor mas alto de la contraccion (puede ser null, de acuerdo si tiene wearable o no)
	@Column(name ="FECHA_INICIO")
	private ZonedDateTime fechaInicio; //fecha de la primera captura de la contraccion
	@Column(name ="FECHA_FIN")
	private ZonedDateTime fechaFin; //fecha de la ultima captura de la contraccion
	@Column(name ="DURACION")
	private Double duracion;
	@Column(name ="FECHA_CREACION")
	private ZonedDateTime fechaCreacion; //fecha y hora final de la captura de la contraccion
	@Column(name ="FECHA_MODIFICACION")
	private ZonedDateTime fechaModificacion;
	@Column(name ="USUARIO_CREACION")
	private String usuarioCreacion;
	@Column(name ="USUARIO_MODIFICACION")
	private String usuarioModificacion;
	private String nivel; //Alta - Intermedia - Baja (?
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="FK_TIPO_CONTRACCION")
	private TipoContraccion tipoContraccion;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMonitoreo")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="FK_MONITOREO", nullable=false)
	private Monitoreo monitoreo;

}
