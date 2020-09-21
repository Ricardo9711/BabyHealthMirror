package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.TipoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAlertaRepository extends JpaRepository<TipoAlerta, Long> {
    public TipoAlerta findByNombre(String name);
}
