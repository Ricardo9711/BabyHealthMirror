package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.TipoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoAlertaRepository extends JpaRepository<TipoAlerta, Long> {
    public TipoAlerta findByNombre(String name);
    List<TipoAlerta> findAllByIdTipoBetween(Long idTipo, Long idTipo2);
}
