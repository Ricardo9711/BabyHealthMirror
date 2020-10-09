package com.upc.babyhealth.models.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CelularPostRequest {
    private String numero;
    private String imei;
    private String operador;
    private String usuarioCreacion;
    private String firebaseToken;
    private Long idUsuario;
}
