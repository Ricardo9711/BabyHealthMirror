package com.upc.babyhealth.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gestante {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private ZonedDateTime fechaNacimiento;
	private ZonedDateTime fechaInicioGestacion;
	private Long dni;
	private Long semanaGestacional;
	private boolean indCompartirUbicacion;
	private boolean tieneDispositivo;
	private String token;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
	@JsonIdentityReference(alwaysAsId = true)
	@OneToOne
	@JoinColumn(name = "FK_USUARIO", updatable = false, nullable = false)
	private Usuario usuario;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name="FK_OBSTETRA", nullable=false)
	private Obstetra obstetra;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CELULAR")
	private List<Celular> celulares;
	public void addCelular(Celular celular){
		celulares.add(celular);
	}

	@OneToMany(mappedBy="gestante",fetch=FetchType.LAZY)
	private List<DispositivoGestante> dispositivos;
	public void addDispositivo(DispositivoGestante dispositivo){
		dispositivos.add(dispositivo);
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Gestante))
			return false;
		Gestante other = (Gestante) obj;
		return Objects.equals(this.apellidoMaterno, other.apellidoMaterno)
			&& Objects.equals(this.apellidoPaterno, other.apellidoPaterno)
			&& Objects.equals(this.dni, other.dni)
			&& Objects.equals(this.id, other.id)
			&& Objects.equals(this.indCompartirUbicacion, other.indCompartirUbicacion)
			&& Objects.equals(this.nombres, other.nombres)
			&& Objects.equals(this.semanaGestacional, other.semanaGestacional);
	}



}
