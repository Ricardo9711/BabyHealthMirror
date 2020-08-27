package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Rol;
import com.upc.babyhealth.models.entity.RolEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    Rol findByNombreRol(RolEnum rol);
}
