package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.request.AuthRequest;
import com.upc.babyhealth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authentication")
    public String generateToken( @RequestBody AuthRequest authRequest) throws Exception {
        try {
            //Primero se verifica si el username y password son correctos
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }catch(Exception ex){
            throw new Exception("Username or Password is invalid");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}
