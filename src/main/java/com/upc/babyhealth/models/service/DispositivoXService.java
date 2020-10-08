package com.upc.babyhealth.models.service;

import com.upc.babyhealth.models.entity.DispositivoX;
import com.upc.babyhealth.models.entity.request.CapturaPostRequest;

import java.time.ZonedDateTime;
import java.util.List;

public interface DispositivoXService {
    List<DispositivoX> findByIdAndDate(Long dispositivoId, String fecha, String hora);
    public boolean saveCaptures(List<CapturaPostRequest> captureRequests, Long idDispositivo);
}
