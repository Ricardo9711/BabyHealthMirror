package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
