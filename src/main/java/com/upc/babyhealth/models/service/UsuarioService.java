package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Usuario;
import com.upc.babyhealth.models.entity.request.SignUpRequest;
import com.upc.babyhealth.models.entity.request.UsuarioPutRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

    public Usuario save(Usuario usuario);

    ResponseEntity<?> registerUser(SignUpRequest signUpRequest) throws Exception;

    List<Usuario> findAll();

    Usuario findByUsername(String username);

    Usuario update(UsuarioPutRequest usuario, Long id);
}
