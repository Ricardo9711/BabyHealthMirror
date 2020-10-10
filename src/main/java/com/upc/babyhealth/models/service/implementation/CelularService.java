package com.upc.babyhealth.models.service.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upc.babyhealth.models.dao.CelularRepository;
import com.upc.babyhealth.models.entity.Celular;
import com.upc.babyhealth.models.entity.Usuario;
import com.upc.babyhealth.models.entity.request.CelularPostRequest;
import com.upc.babyhealth.models.entity.request.CelularPutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class CelularService implements com.upc.babyhealth.models.service.CelularService {

    @Autowired
    private CelularRepository celularRepository;

    @Override
    public Celular insert(CelularPostRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Celular c = new Celular();
        c = mapper.convertValue(request, Celular.class);

        c.setFechaCreacion(ZonedDateTime.now().minusHours(5));
        if(request.getIdUsuario() != null) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(request.getIdUsuario());
            c.setUsuario(usuario);
            c.setEstado("A");
        }
        else{
            c.setEstado("N");
        }

        try{


            c = celularRepository.save(c);

            if(c != null && ( request.getIdUsuario() != null ) ){
                //actualizar todos
                changeState(request.getIdUsuario(), c.getIdCelular());
            }


        }catch(Exception e){
            return null;
        }
        return c;
    }

    @Override
    public Celular update(Long id, CelularPutRequest request) {
        Celular existingCelular = celularRepository.findById(id).orElse(null);
        if(existingCelular != null){
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            Celular newCelular = mapper.convertValue(request,Celular.class);



            if(request.getUsuarioId()!=null && !request.getUsuarioId().equals("")){
                Usuario u = new Usuario();
                u.setIdUsuario(request.getUsuarioId());
                existingCelular.setUsuario(u);
            }
            if(request.getImei()!=null && !request.getImei().equals(""))
                existingCelular.setImei(request.getImei());
            if(request.getOperador()!=null && !request.getOperador().equals(""))
                existingCelular.setOperador(request.getOperador());
            if(request.getUsuarioCreacion()!=null && !request.getUsuarioCreacion().equals(""))
                existingCelular.setUsuarioCreacion(request.getUsuarioCreacion());
            if(request.getFirebaseToken()!=null && !request.getFirebaseToken().equals(""))
                existingCelular.setFirebaseToken(request.getFirebaseToken());
            if(request.getNumero()!=null && !request.getNumero().equals(""))
                existingCelular.setNumero(request.getNumero());

            Celular savedCelular = celularRepository.save(existingCelular);

            if(request.getEstado()!=null && !request.getEstado().equals("")){
                if(request.getEstado() == "A" && existingCelular.getUsuario() != null)
                    changeState(existingCelular.getUsuario().getIdUsuario(),  savedCelular.getIdCelular());

                existingCelular.setEstado(request.getEstado());
            }
        }

        return null;
    }

    @Override
    public boolean changeState(Long usuarioId, Long activeId) {

        //a partir de un usuario, buscar celulares asignados con un estado especifico
        List<Celular> celulares = celularRepository.findByUsuario_IdUsuarioAndEstado(usuarioId,"A" );

        for ( Celular c: celulares) {
            c.setEstado("N");
            try{
                celularRepository.save(c);
            }catch(Exception e){
                return false;
            }
        }

        Celular celular = celularRepository.findById(activeId).orElse(null);
        celular.setEstado("A");
        celularRepository.save(celular);

        return true;
    }

    @Override
    public Celular findActive(Long usuarioId) {
        List<Celular> celulares =celularRepository.findByUsuario_IdUsuarioAndEstado(usuarioId,"A");
        if (celulares.size()>0)
            return celulares.get(0);
        else
            return null;
    }

    @Override
    public List<Celular> findByUsuarioId(Long usuarioId) {
        return celularRepository.findByUsuario_IdUsuario(usuarioId);
    }


}
