package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "gestantes","celulares"})
public class Obstetra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombreObstetra;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Long dni;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "gestantes"})
	@OneToMany(mappedBy="obstetra",fetch=FetchType.LAZY)
	private List<Gestante> gestantes;
	
	@OneToOne
	@JoinColumn(name = "FK_USUARIO", updatable = false, nullable = false)
	private Usuario usuario;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CELULAR")
	private List<Celular> celulares;
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name="FK_CENTRO_SALUD", nullable=false)
	private CentroSalud centroSalud;
	

	public void addCelular(Celular celular){
		celulares.add(celular);
	}


	public void addGestante(Gestante gestante){
			gestantes.add(gestante);
	}

}
