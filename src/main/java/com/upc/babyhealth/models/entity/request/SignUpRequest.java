package com.upc.babyhealth.models.entity.request;

import com.upc.babyhealth.models.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class SignUpRequest {

    @NotNull(message = "User must not be null")
    private Usuario newUser;
    @NotNull(message = "Entity must not be null")
    private Object entity;
    @NotBlank(message="Role is mandatory")
    private String role;
    @NotBlank(message="Admin username is mandatory")
    private String creator;

    /*
    public SignUpRequest(){
        newUser.setFechaCreacion(ZonedDateTime.now());
        newUser.setFechaModificacion(null);
        newUser.setUsuarioCreacion(null);
        newUser.setUsuarioModificacion(null);
    }

     */
}
