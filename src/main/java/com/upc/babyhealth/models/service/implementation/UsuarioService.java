package com.upc.babyhealth.models.service.implementation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upc.babyhealth.models.dao.GestanteRepository;
import com.upc.babyhealth.models.dao.ObstetraRepository;
import com.upc.babyhealth.models.dao.RolRepository;
import com.upc.babyhealth.models.dao.UsuarioRepository;
import com.upc.babyhealth.models.entity.*;
import com.upc.babyhealth.models.entity.request.ChangePasswordRequest;
import com.upc.babyhealth.models.entity.request.SignUpRequest;
import com.upc.babyhealth.models.entity.request.UsuarioPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService implements com.upc.babyhealth.models.service.UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private GestanteService gestanteService;
    @Autowired
    private ObstetraService obstetraService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByNombreUsuario(username);
    }

    @Override
    public Usuario update(UsuarioPutRequest usuarioPutRequest, Long id) {
        Usuario existingUser = usuarioRepository.findById(id).orElse(null);
        if(existingUser!=null){
            //update
            if(usuarioPutRequest.getEmail()!=null && !usuarioPutRequest.getEmail().equals(""))
                existingUser.setEmail(usuarioPutRequest.getEmail());
            if(usuarioPutRequest.getNroCelular()!=null && !usuarioPutRequest.getNroCelular().equals(""))
                existingUser.setNroCelular(usuarioPutRequest.getNroCelular());
            return usuarioRepository.save(existingUser);
        }
        return existingUser;
    }

    @Override
    public Usuario changePassword(Long id, ChangePasswordRequest request) {
        Usuario existingUser = usuarioRepository.findById(id).orElse(null);
        if(existingUser != null){
            if(passwordEncoder.matches(request.getOldPassword(),existingUser.getContrasenia())){
                existingUser.setContrasenia(passwordEncoder.encode(request.getNewPassword()));
                return usuarioRepository.save(existingUser);
            }
        }
        return null;
    }

    @Override
    public Usuario resetPassword(Long id) {
        Usuario existingUser = usuarioRepository.findById(id).orElse(null);
        if(existingUser != null){
            existingUser.setContrasenia(passwordEncoder.encode("Babyhealth123"));
            return usuarioRepository.save(existingUser);
        }
        return null;
    }


    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) throws Exception{

        if(usuarioRepository.existsByNombreUsuario(signUpRequest.getNewUser().getNombreUsuario())){
            return ResponseEntity
                    .badRequest()
                    .body("El nombre de usuario ya existe");
        }
        else{

            Usuario newUser = new Usuario();

            newUser.setNombreUsuario(signUpRequest.getNewUser().getNombreUsuario().toUpperCase());
            newUser.setContrasenia(passwordEncoder.encode(signUpRequest.getNewUser().getContrasenia()));
            newUser.setUsuarioCreacion(signUpRequest.getNewUser().getUsuarioCreacion().toUpperCase());
            //setearle un fixed zone UTF-5
            newUser.setFechaCreacion(ZonedDateTime.now(ZoneId.of("America/Lima")));

            //Asignacion y validacion del estado del usuario
            if (signUpRequest.getNewUser().getEstado() == null || signUpRequest.getNewUser().getEstado().toString() == ""){
                newUser.setEstado(UsuarioEstadoEnum.A);
            }
            else{
                found: {
                    for (UsuarioEstadoEnum estado: UsuarioEstadoEnum.values()){
                        if( signUpRequest.getNewUser().getEstado() == estado ){
                            newUser.setEstado(signUpRequest.getNewUser().getEstado());
                            break found;
                        }
                    }
                    return ResponseEntity
                            .badRequest()
                            .body("El estado del usuario es invalido");
                }
            }


            //now switch among roles
            ObjectMapper mapper = new ObjectMapper();
            //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            mapper.registerModule(new JavaTimeModule());
            Rol userRole;
            switch (signUpRequest.getRole()){

                case "ADMINISTRADOR":
                    userRole = rolRepository.findByNombreRol(RolEnum.ADMINISTRADOR);
                    newUser.setRol(userRole);
                    usuarioRepository.save(newUser);
                    break;

                case "GESTANTE":
                    Gestante newGestante = mapper.convertValue(signUpRequest.getEntity(), Gestante.class);

                    if( gestanteService.findByDni(newGestante.getDni()) != null){
                        return ResponseEntity
                                .badRequest()
                                .body("el DNI ya existe");
                    }

                    newGestante.setUsuario(newUser);
                    userRole = rolRepository.findByNombreRol(RolEnum.GESTANTE);
                    newUser.setRol(userRole);

                    usuarioRepository.save(newUser);
                    gestanteService.save(newGestante);
                    break;

                case "OBSTETRA":
                    Obstetra newObstetra = mapper.convertValue(signUpRequest.getEntity(), Obstetra.class);
                    if(obstetraService.findByDni(newObstetra.getDni()) != null )
                        return ResponseEntity
                                .badRequest()
                                .body("el DNI ya existe");

                    userRole = rolRepository.findByNombreRol(RolEnum.OBSTETRA);
                    newUser.setRol(userRole);
                    newObstetra.setUsuario(newUser);

                    usuarioRepository.save(newUser);
                    obstetraService.save(newObstetra);
                    break;

                default:
                    return ResponseEntity
                            .badRequest()
                            .body("Invalid Role");
            }

        }

        return null;
    }
}
