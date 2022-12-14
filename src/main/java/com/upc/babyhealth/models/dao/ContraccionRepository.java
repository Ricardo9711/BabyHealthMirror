package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Contraccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContraccionRepository extends JpaRepository<Contraccion, Long> {
    List<Contraccion> findByMonitoreo_IdMonitoreo(Long monitoreoId);
}
