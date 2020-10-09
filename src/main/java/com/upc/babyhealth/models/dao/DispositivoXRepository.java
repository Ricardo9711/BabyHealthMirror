package com.upc.babyhealth.models.dao;

import com.upc.babyhealth.models.entity.Dispositivo;
import com.upc.babyhealth.models.entity.DispositivoX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

public interface DispositivoXRepository extends JpaRepository<DispositivoX, Long> {

    //String query = String.format("SELECT * FROM %tableName WHERE FECHA_EVENTO = %date ", String  );
    @Query(value = "SELECT * FROM DISPOSITIVO_?1 WHERE FECHA_EVENTO = ?2 ", nativeQuery = true)
    List<DispositivoX> findCaptureByDispositivoIdAndDate(Long dispositivoId,String date);

    //@Query(value = "insert into dispositivo_?1 (evento,fecha_creacion,fecha_evento,id_dispositivo,usuario_creacion,valor_registrado) values (?2,?3 ?4 ?5,?6,?7)", nativeQuery = true)
    /*
    @Transactional
    @Query(value = "insert into dispositivo_1  (evento,fecha_creacion,fecha_evento,id_dispositivo,usuario_creacion,valor_registrado) values (?1,?2 ?3 ?4,?5,?6))", nativeQuery = true)
    void save(Long dispositivoId, String evento, String fechaCreacion, String fechaEvento, Long dispositiovId2 , String usuarioCreacion, Double valorRegistrado );
*/

    @Transactional
    @Query(
            value = "insert into dispositivo_:dispositivoId (evento,fecha_creacion,fecha_evento,id_dispositivo,usuario_creacion,valor_registrado)"
                    + "values (:evento, :fechaCreacion, :fechaEvento, :dispositiovId2, :usuarioCreacion, :valorRegistrado)",
            nativeQuery = true)
    void insertDispositivoX(@Param("dispositivoId") Long dispositivoId, @Param("evento") String evento,
                            @Param("fechaCreacion") String fechaCreacion, @Param("fechaEvento") String fechaEvento,
                            @Param("dispositiovId2") Long dispositiovId2 , @Param("usuarioCreacion") String usuarioCreacion,
                            @Param("valorRegistrado") Double valorRegistrado);

    @Procedure
    void SP_NEW_TABLE_DISPOSITIVOX(Long dispositivoId);

    @Procedure
    void SP_INSERT_CAPTURA(Long dispositivoId, String evento, String fechaCreacion, String fechaEvento, String usuarioCreacion, Double valorRegistrado);
}
