package com.upc.babyhealth.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Familiar {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idFamiliar;
	private Long dni;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String parentesco;
	private Long numeroCelular;
	private String operador;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name="FK_GESTANTE", nullable=false)
	private Gestante gestante;
	

}
