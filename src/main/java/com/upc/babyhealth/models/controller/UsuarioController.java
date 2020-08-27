package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Usuario;
import com.upc.babyhealth.models.entity.request.SignUpRequest;
import com.upc.babyhealth.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        return usuarioService.registerUser(signUpRequest);
    }

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }
}
