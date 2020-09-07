package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Dispositivo;
import com.upc.babyhealth.models.entity.DispositivoGestante;
import com.upc.babyhealth.models.entity.Gestante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DispositivoGestanteRepository extends JpaRepository<DispositivoGestante, Long> {
    public List<DispositivoGestante> findByGestanteAndDispositivo(Gestante gestante, Dispositivo dispositivo);
    public List<DispositivoGestante> findByGestante(Gestante gestante);
    public List<DispositivoGestante> findByDispositivo(Dispositivo dispositivo);
}
