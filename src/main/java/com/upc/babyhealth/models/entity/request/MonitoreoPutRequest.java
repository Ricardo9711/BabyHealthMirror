package com.upc.babyhealth.models.entity.request;

import com.upc.babyhealth.models.entity.MonitoreoEstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitoreoPutRequest {
    private ZonedDateTime fechaFin;
    private MonitoreoEstadoEnum estado;
    private Double contraccionesPromedio;	//cuantas contracciones hay en 10 minutos. 10 min es el maximo de un monitoreo
    private Double frecuenciaPromedio; //frecuencia va a ser igual a contraccionesPromedio
    private Double tiempoEcPromedio; //Espacio de tiempo entre el fin de una contraccion y el inicio del otro
    private Double intensidadPromedio; //Intensidad promedio: 0 si es manual y otro
    private ZonedDateTime fechaModificacion; //va a ser igual a la fecha fin
    private Integer semanaGestacion; //semana de gestacion del monitoreo
    private String usuarioModificacion;
}
