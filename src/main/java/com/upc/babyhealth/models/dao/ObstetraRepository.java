package com.upc.babyhealth.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.babyhealth.models.entity.Obstetra;

import java.util.List;
import java.util.Optional;


@Repository
public interface ObstetraRepository extends JpaRepository<Obstetra, Long>{
	
	Optional<Obstetra> findByDni(Long dni);

}
