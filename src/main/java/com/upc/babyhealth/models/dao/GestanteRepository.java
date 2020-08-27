package com.upc.babyhealth.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.upc.babyhealth.models.entity.Gestante;

@Repository
public interface GestanteRepository extends JpaRepository<Gestante, Long>{
	
	Optional<Gestante> findByDni(Long dni);
	
}
