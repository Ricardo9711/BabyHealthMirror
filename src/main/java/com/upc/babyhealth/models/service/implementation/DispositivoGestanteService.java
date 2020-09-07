package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.DispositivoGestanteRepository;
import com.upc.babyhealth.models.entity.Dispositivo;
import com.upc.babyhealth.models.entity.DispositivoGestante;
import com.upc.babyhealth.models.entity.Gestante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoGestanteService implements com.upc.babyhealth.models.service.DispositivoGestanteService {

    @Autowired
    DispositivoGestanteRepository dispositivoGestanteRepository;

    @Override
    public List<DispositivoGestante> findAll() {
        return dispositivoGestanteRepository.findAll();
    }

    @Override
    public DispositivoGestante save(DispositivoGestante dispositivo) {
        return dispositivoGestanteRepository.save(dispositivo);
    }

    @Override
    public DispositivoGestante findById(Long id) {
        return dispositivoGestanteRepository.findById(id).orElse(null);
    }

    @Override
    public DispositivoGestante update(DispositivoGestante dispositivo) {
        //TODO
        return dispositivoGestanteRepository.save(dispositivo);
    }

    @Override
    public List<DispositivoGestante> findByGestanteIdAndDispositivoId(Long gestanteId, Long dispositivoId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setIdDispositivo(dispositivoId);
        return dispositivoGestanteRepository.findByGestanteAndDispositivo(gestante, dispositivo);
    }

    @Override
    public List<DispositivoGestante> findByGestanteId(Long gestanteId) {
        Gestante gestante = new Gestante();
        gestante.setId(gestanteId);
        return dispositivoGestanteRepository.findByGestante(gestante);
    }

    @Override
    public List<DispositivoGestante> findByDispositivoId(Long dispositivoId) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setIdDispositivo(dispositivoId);
        return dispositivoGestanteRepository.findByDispositivo(dispositivo);
    }
}
