package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.FamiliarRepository;
import com.upc.babyhealth.models.entity.Familiar;
import com.upc.babyhealth.models.entity.Gestante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamiliarService implements com.upc.babyhealth.models.service.FamiliarService {

    @Autowired
    private FamiliarRepository familiarRepository;

    @Override
    public Familiar save(Familiar familiar, Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        familiar.setGestante(gestante);
        return familiarRepository.save(familiar);
    }

    @Override
    public List<Familiar> findAllByGestante(Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return familiarRepository.findByGestante(gestante);
    }

    @Override
    public Familiar update(Long gestanteId, Long familiarId, Familiar modifiedFamiliar) {
        Familiar existingFamiliar = familiarRepository.findById(familiarId).orElse(null);
        if (existingFamiliar == null)
            return null;

        if(modifiedFamiliar.getNombres() != null && !modifiedFamiliar.getNombres().equals(""))
            existingFamiliar.setNombres(modifiedFamiliar.getNombres());
        if(modifiedFamiliar.getApellidoPaterno() != null && !modifiedFamiliar.getApellidoPaterno().equals(""))
            existingFamiliar.setApellidoPaterno(modifiedFamiliar.getApellidoPaterno());
        if(modifiedFamiliar.getApellidoMaterno()!=null && !modifiedFamiliar.getApellidoMaterno().equals(""))
            existingFamiliar.setApellidoMaterno(modifiedFamiliar.getApellidoMaterno());
        if(modifiedFamiliar.getDni()!=null && !modifiedFamiliar.getDni().toString().equals(""))
            existingFamiliar.setDni(modifiedFamiliar.getDni());
        if(modifiedFamiliar.getGestante()!=null)
            existingFamiliar.setGestante(modifiedFamiliar.getGestante());
        if(modifiedFamiliar.getNumeroCelular()!=null && !modifiedFamiliar.getNumeroCelular().toString().equals(""))
            existingFamiliar.setNumeroCelular(modifiedFamiliar.getNumeroCelular());
        if(modifiedFamiliar.getParentesco()!=null && !modifiedFamiliar.getParentesco().equals(""))
            existingFamiliar.setParentesco(modifiedFamiliar.getParentesco());
        if(modifiedFamiliar.getOperador()!=null && !modifiedFamiliar.getOperador().equals(""))
            existingFamiliar.setOperador(modifiedFamiliar.getOperador());

        return familiarRepository.save(existingFamiliar);
    }

    @Override
    public void delete(Long gestanteId, Long familiarId) {
        familiarRepository.deleteById(familiarId);
    }
}
