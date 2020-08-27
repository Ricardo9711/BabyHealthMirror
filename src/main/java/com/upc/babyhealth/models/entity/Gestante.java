package com.upc.babyhealth.models.entity;

import lombok.*;

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
@ToString
public class Gestante {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Long edad;
	private Long dni;
	private Long semanaGestacional;
	private boolean indCompartirUbicacion;

	@OneToOne
	@JoinColumn(name = "FK_USUARIO", updatable = false, nullable = false)
	private Usuario usuario;


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


	/*
	@Override
	public int hashCode() {
		return Objects.hash(this.apellidoMaterno,
							this.apellidoPaterno,
							this.dni,
							this.edad,
							this.id,
							this.indCompartirUbicacion,
							this.nombres,
							this.semanaGestacional);
	}

	 */
	
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
			&& Objects.equals(this.edad, other.edad)
			&& Objects.equals(this.id, other.id)
			&& Objects.equals(this.indCompartirUbicacion, other.indCompartirUbicacion)
			&& Objects.equals(this.nombres, other.nombres)
			&& Objects.equals(this.semanaGestacional, other.semanaGestacional);
	}



}
