package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Contraccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContraccionRepository extends JpaRepository<Contraccion, Long> {
}
