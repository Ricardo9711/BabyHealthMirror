package com.upc.babyhealth.models.entity;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Dispositivo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDispositivo;
	private String numeroSerie;
	private String estado;
	private ZonedDateTime fechaCreacion;
	private ZonedDateTime fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;
	
	@OneToMany(mappedBy="dispositivo",fetch=FetchType.LAZY)
	private List<DispositivoGestante> dispositivos;
	public void addDispositivo(DispositivoGestante dispositivo){
		dispositivos.add(dispositivo);
	}
}
