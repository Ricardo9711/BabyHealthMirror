package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.ObstetraRepository;
import com.upc.babyhealth.models.entity.CentroSalud;
import com.upc.babyhealth.models.entity.Obstetra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObstetraService implements com.upc.babyhealth.models.service.ObstetraService {

    @Autowired
    private ObstetraRepository obstetraRepository;

    @Override
    public Obstetra findByDni(Long dni) {

        return obstetraRepository.findByDni(dni);
    }

    @Override
    public Obstetra save(Obstetra obstetra) {
        return obstetraRepository.save(obstetra);
    }

    @Override
    public Obstetra findById(Long id) {
        return obstetraRepository.findById(id).orElse(null);
    }


    @Override
    public Obstetra update(Obstetra obstetra, Long obstetraId) {
        Obstetra existingObstetra = obstetraRepository.findById(obstetraId).orElse(null);
        if(existingObstetra != null) {

            if(obstetra.getDni()!=null && obstetra.getDni().toString() != "" )
                existingObstetra.setDni(obstetra.getDni());
            if(obstetra.getNombreObstetra()!=null && obstetra.getNombreObstetra()!="")
                existingObstetra.setNombreObstetra(obstetra.getNombreObstetra());
            if(obstetra.getApellidoMaterno()!=null && obstetra.getApellidoMaterno()!="")
                existingObstetra.setApellidoMaterno(obstetra.getApellidoMaterno());
            if(obstetra.getApellidoPaterno()!=null && obstetra.getApellidoPaterno()!="")
                existingObstetra.setApellidoPaterno(obstetra.getApellidoPaterno());
            if(obstetra.getCentroSalud().getIdCentroSalud()!=null && obstetra.getCentroSalud().getIdCentroSalud().toString()!="")
            {
                CentroSalud newCentroSalud = new CentroSalud();
                newCentroSalud.setIdCentroSalud(obstetra.getCentroSalud().getIdCentroSalud());
                existingObstetra.setCentroSalud(newCentroSalud);
            }
        }
        return obstetraRepository.save(existingObstetra);
    }
}
