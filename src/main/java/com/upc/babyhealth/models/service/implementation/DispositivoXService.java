package com.upc.babyhealth.models.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upc.babyhealth.models.dao.DispositivoXRepository;
import com.upc.babyhealth.models.entity.DispositivoX;
import com.upc.babyhealth.models.entity.request.CapturaPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
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

    @Override
    public boolean saveCaptures(List<CapturaPostRequest> captureRequests, Long dispositivoId){
        for ( CapturaPostRequest captura: captureRequests) {
            /*
            DispositivoX capturaDisp = new DispositivoX();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            capturaDisp = mapper.convertValue(captura, DispositivoX.class);
            capturaDisp.setIdDispositivo(idDispositivo);
            capturaDisp.setEvento("AUTOREPLAY");
            capturaDisp.setFechaCreacion(ZonedDateTime.now());
            capturaDisp.setUsuarioCreacion("MASTER");
             */

            String fechaEvento = captura.getFechaEvento().toString();
            String fechaCreacion = ZonedDateTime.now(ZoneId.of("America/Lima")).toString();
            try{
                dispositivoXRepository.SP_INSERT_CAPTURA(dispositivoId,"AUTOREPLAY",fechaCreacion, fechaEvento,  "MASTER", captura.getValorRegistrado());
                //dispositivoXRepository.insertDispositivoX(1L,"2020-09-21T13:33:20Z[UTC]", "2020-09-21T13:33:20Z[UTC]", "2020-09-21T13:33:20Z[UTC]",1L, "MASTER",9.99);

            }catch(Exception e){
                return false;
            }

        }
        return true;
    }
}
