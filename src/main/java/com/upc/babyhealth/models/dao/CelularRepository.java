package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Celular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CelularRepository extends JpaRepository<Celular, Long> {
    List<Celular> findByUsuario_IdUsuarioAndEstado(Long idUsuario, String estado);
}
