package com.upc.babyhealth.models.entity.request;

import com.upc.babyhealth.models.entity.TipoContraccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContraccionRequest {
    private Double intensidad; //valor mas alto de la contraccion
    private ZonedDateTime fechaInicio; //fecha de la primera captura de la contraccion
    private ZonedDateTime fechaFin; //fecha de la ultima captura de la contraccion
    private Double duracion;
    private ZonedDateTime fechaCreacion; //fecha y hora final de la captura de la contraccion
    //private ZonedDateTime fechaModificacion;
    private String usuarioCreacion;
    //private String usuarioModificacion;
    private String nivel; //Alta - Intermedia - Baja (?
    private TipoContraccion tipoContraccion;
    private Long monitoreoId;
}
