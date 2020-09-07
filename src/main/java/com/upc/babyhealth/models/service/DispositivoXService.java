package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.DispositivoX;

import java.time.ZonedDateTime;
import java.util.List;

public interface DispositivoXService {
    List<DispositivoX> findByIdAndDate(Long dispositivoId, String fecha, String hora);
}
