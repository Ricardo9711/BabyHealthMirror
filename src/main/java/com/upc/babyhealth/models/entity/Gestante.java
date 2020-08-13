package com.upc.babyhealth.models.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gestante {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String role;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Long edad;
	private Long dni;
	private Long semanaGestacional;
	private boolean indCompartirUbicacion;
	
	
	
	
	public Gestante() {}
	
	public Gestante(Long id,
					String role,
					String nombres,
					String apellidoPaterno,
					String apellidoMaterno,
					Long edad,
					Long dni,
					Long semanaGestacional,
					boolean indCompartirUbicacion) {
		this.id = id;
		this.role = role;
		this.nombres = nombres;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.edad = edad;
		this.dni = dni;
		this.semanaGestacional = semanaGestacional;
		this.indCompartirUbicacion = indCompartirUbicacion;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	
	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public Long getEdad() {
		return edad;
	}
	
	public void setEdad(Long edad) {
		this.edad = edad;
	}
	
	public Long getDni() {
		return dni;
	}
	
	public void setDni(Long dni) {
		this.dni = dni;
	}
	
	public Long getSemanaGestacional() {
		return semanaGestacional;
	}
	
	public void setSemanaGestacional(Long semanaGestacional) {
		this.semanaGestacional = semanaGestacional;
	}
	
	public boolean isIndCompartirUbicacion() {
		return indCompartirUbicacion;
	}
	
	public void setIndCompartirUbicacion(boolean indCompartirUbicacion) {
		this.indCompartirUbicacion = indCompartirUbicacion;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(this.apellidoMaterno,
							this.apellidoPaterno,
							this.dni,
							this.edad,
							this.id,
							this.indCompartirUbicacion,
							this.nombres,
							this.role,
							this.semanaGestacional);
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
			&& Objects.equals(this.edad, other.edad)
			&& Objects.equals(this.id, other.id)
			&& Objects.equals(this.indCompartirUbicacion, other.indCompartirUbicacion)
			&& Objects.equals(this.nombres, other.nombres)
			&& Objects.equals(this.role, other.role)
			&& Objects.equals(this.semanaGestacional, other.semanaGestacional);
	}

	@Override
	public String toString() {
		return "Gestante{" +"id=" + this.id +
							",role='" + this.role + '\'' +
							",nombres='" + this.nombres + '\'' + 
							",apellidoPaterno='" + this.apellidoPaterno + '\'' + 
							",apellidoMaterno='" + this.apellidoMaterno + '\'' +
							",edad='" + this.edad + '\'' +
							",dni='" + this.dni + '\'' + 
							",semanaGestacional='" + this.semanaGestacional + '\'' + 
							",indCompartirUbicacion='" + this.indCompartirUbicacion + '\'' + '}';
	}

	
	
}
