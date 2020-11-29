package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
	
	public Alerta findTopByGestanteAndFechaVistoIsNullOrderByFechaCreacionDesc(Long IdGestante);
}
