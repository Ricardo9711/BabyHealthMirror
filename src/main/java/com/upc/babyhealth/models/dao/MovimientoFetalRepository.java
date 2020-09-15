package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.MovimientoFetal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoFetalRepository extends JpaRepository<MovimientoFetal, Long> {
}
