package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upc.babyhealth.models.entity.Obstetra;

import java.util.List;
import java.util.Optional;


@Repository
public interface ObstetraRepository extends JpaRepository<Obstetra, Long>{
	
	Obstetra findByDni(Long dni);
    Obstetra findByUsuario(Usuario usuario);
    Obstetra findByUsuario_NombreUsuario(String username);
    Obstetra findByUsuario_IdUsuario(Long id);

}
