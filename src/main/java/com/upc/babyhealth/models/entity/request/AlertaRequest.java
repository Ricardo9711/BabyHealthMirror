package com.upc.babyhealth.models.entity.request;

import com.upc.babyhealth.models.entity.Alerta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertaRequest {

    private Double intensidadMmhg;
    //private ZonedDateTime fechaCreacion;
    private String usuarioCreacion;
    //private ZonedDateTime fechaVisto;
    //private String usuarioVisto;
    private String tipoAlerta;
    private Long gestanteId;
    private String gestanteToken;
    private String obstetraToken;
}
