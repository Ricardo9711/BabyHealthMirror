package com.upc.babyhealth.models.service.implementation;

import com.upc.babyhealth.models.dao.DispositivoXRepository;
import com.upc.babyhealth.models.entity.DispositivoX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class DispositivoXService implements com.upc.babyhealth.models.service.DispositivoXService {

    @Autowired
    private DispositivoXRepository dispositivoXRepository;

    @Override
    public List<DispositivoX> findByIdAndDate(Long dispositivoId, String fecha, String hora) {
        String fechaYHora = fecha + " " + hora;
        //String tableName = "DISPOSITIVO_" + dispositivoId.toString();

        //return dispositivoXRepository.findCaptureByDispositivoIdAndDate(tableName,fechaYHora);
        return dispositivoXRepository.findCaptureByDispositivoIdAndDate(dispositivoId,fechaYHora);
    }
}
