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
	private Double intensidad;
	@Column(name ="FECHA_INICIO")
	private ZonedDateTime fechaInicio;
	@Column(name ="FECHA_FIN")
	private ZonedDateTime fechaFin;
	@Column(name ="DURACION")
	private Double duracion;
	@Column(name ="FECHA_CREACION")
	private ZonedDateTime fechaCreacion;
	@Column(name ="FECHA_MODIFICACION")
	private ZonedDateTime fechaMoificacion;
	@Column(name ="USUARIO_CREACION")
	private String usuarioCreacion;
	@Column(name ="USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name="FK_TIPO_CONTRACCION", nullable=false)
	private TipoContraccion tipoContraccion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="FK_MONITOREO", nullable=false)
	private Monitoreo monitoreo;

}
