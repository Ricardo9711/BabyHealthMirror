package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.entity.Monitoreo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface MonitoreoRepository extends JpaRepository<Monitoreo, Long> {
    public List<Monitoreo> findBySemanaGestacionAndGestante(int semana, Gestante gestante);
    public List<Monitoreo> findByGestante_Id(Long gestanteId);

    //recuperar el monitoreo mas reciente
    //ordenar por fecha creacion
    public Monitoreo findTopByGestanteAndEstadoOrderByFechaCreacionDesc(Gestante gestante, String estado);
}
