package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Dispositivo;
import com.upc.babyhealth.models.entity.DispositivoX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface DispositivoXRepository extends JpaRepository<DispositivoX, Long> {

    @Query(value = "{call SP_NEW_TABLE_DISPOSITIVOX(:idx) }", nativeQuery = true)
    void createTable(@Param("idx") Long idx );

    @Query(value = "SELECT * FROM  :tableName WHERE FECHA_EVENTO = :date ", nativeQuery = true)
    List<DispositivoX> findCaptureByDispositivoIdAndDate(@Param("tableName") String tableName, @Param("date") String date);
}
