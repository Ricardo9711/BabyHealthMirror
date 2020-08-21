package com.upc.babyhealth.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.babyhealth.models.entity.Obstetra;


@Repository
public interface ObstetraRepository extends JpaRepository<Obstetra, Long>{
	
	

}
