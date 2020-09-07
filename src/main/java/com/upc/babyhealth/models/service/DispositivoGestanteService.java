package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.Dispositivo;
import com.upc.babyhealth.models.entity.DispositivoGestante;

import java.util.List;

public interface DispositivoGestanteService {

	public List<DispositivoGestante> findAll();
	public DispositivoGestante save(DispositivoGestante dispositivo);
	public DispositivoGestante findById(Long id);
	public DispositivoGestante update(DispositivoGestante dispositivo);
	public List<DispositivoGestante> findByGestanteIdAndDispositivoId(Long gestanteId, Long dispositivoId);
	public List<DispositivoGestante> findByGestanteId(Long gestanteId);
	public List<DispositivoGestante> findByDispositivoId(Long dispositivoId);
}
