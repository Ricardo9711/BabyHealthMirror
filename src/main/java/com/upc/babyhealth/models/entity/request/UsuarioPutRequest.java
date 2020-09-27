package com.upc.babyhealth.models.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPutRequest {

    String email;
    String nroCelular;
}
