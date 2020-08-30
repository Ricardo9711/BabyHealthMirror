package com.upc.babyhealth.models.dao;

import java.util.List;
import java.util.Optional;

import com.upc.babyhealth.models.entity.Monitoreo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.upc.babyhealth.models.entity.Gestante;

@Repository
public interface GestanteRepository extends JpaRepository<Gestante, Long>{

	Gestante findByDni(Long dni);
}
