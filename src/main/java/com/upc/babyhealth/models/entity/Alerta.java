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
public class Alerta {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idAlerta;
    private Double intensidadMmhg;
    private ZonedDateTime fechaCreacion;
    private String usuarioCreacion;
    private ZonedDateTime fechaVisto;
    private String usuarioVisto;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="id_gestante", nullable=false)
    private Gestante gestante;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTipo")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipo", nullable=false)
    private TipoAlerta tipoAlerta;

}
