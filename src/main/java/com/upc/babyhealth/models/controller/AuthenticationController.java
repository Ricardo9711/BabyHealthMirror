package com.upc.babyhealth.models.controller;

import com.upc.babyhealth.models.entity.request.AuthRequest;
import com.upc.babyhealth.util.JwtUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authentication;
        try {
            //Primero se verifica si el username y password son correctos
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }catch(Exception ex){
            throw new Exception("Username or Password is invalid");
        }




        return ResponseEntity
                .ok()
                .body(jwtUtil.generateToken(authentication));

    }
}