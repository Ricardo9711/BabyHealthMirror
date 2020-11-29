package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
	
	public Alerta findTopByGestante_IdAndFechaVistoIsNullOrderByFechaCreacionDesc(Long IdGestante);

	public List<Alerta> findAlertasByGestante_Id(Long idGestante);

}
