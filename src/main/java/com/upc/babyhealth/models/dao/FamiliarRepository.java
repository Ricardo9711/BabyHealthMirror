package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Familiar;
import com.upc.babyhealth.models.entity.Gestante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamiliarRepository extends JpaRepository<Familiar, Long> {

    public List<Familiar> findByGestante(Gestante gestante);
}
