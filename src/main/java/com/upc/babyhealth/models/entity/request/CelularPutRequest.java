package com.upc.babyhealth.models.entity.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CelularPutRequest {
    private String numero;
    private String imei;
    private String operador;
    private String usuarioCreacion;
    private String estado;
    private String firebaseToken;
    private Long usuarioId;
}
