package com.upc.babyhealth.models.entity;

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
    private ZonedDateTime usuarioCreacion;
    private ZonedDateTime fechaVisto;
    private String usuarioVisto;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="id_gestante", nullable=false)
    private Gestante gestante;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipo", nullable=false)
    private TipoAlerta tipoAlerta;

}
