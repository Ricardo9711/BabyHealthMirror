package com.upc.babyhealth.models.entity.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CelularPutRequest {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long numero;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long imei;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String operador;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usuarioCreacion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String estado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firebaseToken;
}
