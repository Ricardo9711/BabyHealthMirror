package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.MovimientoFetal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoFetalRepository extends JpaRepository<MovimientoFetal, Long> {
    public List<MovimientoFetal> findByMonitoreo_IdMonitoreo(Long monitoreoId);
}
