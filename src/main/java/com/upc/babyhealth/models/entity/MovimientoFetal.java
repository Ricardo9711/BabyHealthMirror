package com.upc.babyhealth.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoFetal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idMovimientoFetal;
    private ZonedDateTime fechaCreacion;
    private String usuarioCreacion;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMonitoreo")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="fk_monitoreo", nullable=false)
    private Monitoreo monitoreo;

}
