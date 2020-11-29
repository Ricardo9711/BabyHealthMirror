package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.Usuario;
import com.upc.babyhealth.models.entity.request.ChangePasswordRequest;
import com.upc.babyhealth.models.entity.request.SignUpRequest;
import com.upc.babyhealth.models.entity.request.UsuarioPutRequest;
import com.upc.babyhealth.models.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
        return usuarioService.registerUser(signUpRequest);
    }

    @GetMapping(params = "username")
    public Usuario findByUsername(@RequestParam String username){
        return usuarioService.findByUsername(username);
    }

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @PutMapping("/{id}")
    public Usuario update(@RequestBody UsuarioPutRequest usuario, @PathVariable Long id){
        return usuarioService.update(usuario, id);
    }

    //HABILITARLO TAMBIEN PARA GESTANTES Y OBSTETRAS
    @PutMapping("/{id}/contrasenia")
    public Usuario changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request){
        return usuarioService.changePassword(id, request);
    }

    @PostMapping("/{id}/contrasenia")
    public Usuario resetPassword(@PathVariable Long id){
        return usuarioService.resetPassword(id);
    }
}
