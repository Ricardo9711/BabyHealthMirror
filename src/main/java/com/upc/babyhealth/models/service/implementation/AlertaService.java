package com.upc.babyhealth.models.service.implementation;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.upc.babyhealth.models.dao.AlertaRepository;
import com.upc.babyhealth.models.entity.Alerta;
import com.upc.babyhealth.models.entity.Familiar;
import com.upc.babyhealth.models.entity.Gestante;
import com.upc.babyhealth.models.entity.TipoAlerta;
import com.upc.babyhealth.models.entity.request.AlertaRequest;
import com.upc.babyhealth.models.service.FamiliarService;
import com.upc.babyhealth.models.service.GestanteService;
import com.upc.babyhealth.models.service.SmsService;
import com.upc.babyhealth.models.service.TipoAlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AlertaService implements com.upc.babyhealth.models.service.AlertaService {
    @Autowired
    private AlertaRepository alertaRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private GestanteService gestanteService;
    @Autowired
    private FamiliarService familiarService;
    @Autowired
    private TipoAlertaService tipoAlertaService;
    @Autowired
    private PushNotificationService pushNotificationService;


    @Override
    public Alerta sendAlert(AlertaRequest alertaRequest) {
        //Debe enviar push notifications a gestante y obstetra
        Alerta alerta = new Alerta();
        Gestante gestante = gestanteService.findOne(alertaRequest.getGestanteId());
        alerta.setGestante(gestante);
        alerta.setTipoAlerta(tipoAlertaService.findByName(alertaRequest.getTipoAlerta()));
        alerta.setIntensidadMmhg(alertaRequest.getIntensidadMmhg());
        alerta.setUsuarioCreacion(alertaRequest.getUsuarioCreacion());
        alerta.setFechaCreacion(ZonedDateTime.now().minusHours(5));

        if(alertaRequest.getTipoAlerta().equals("MONITOREO"))
            pushNotificationService.notifyFinishedMonitoring(alertaRequest.getGestanteToken(),alertaRequest.getObstetraToken());
        else
            pushNotificationService.notifyAlert(alerta, alertaRequest.getGestanteToken(),alertaRequest.getObstetraToken());

        if(!alertaRequest.getTipoAlerta().equals("MONITOREO"))
            this.sendSmsToFam(alerta);

        //cambiar estado gestante
        if(!alertaRequest.getTipoAlerta().equals("MONITOREO") && !alertaRequest.getTipoAlerta().equals("EMERGENCIA") && !alertaRequest.getTipoAlerta().equals("LABOR DE PARTO"))
        {
            gestante.setEstado("EMERGENCIA");
            gestanteService.update(gestante);
        }
        return alertaRepository.save(alerta);
    }

    @Override
    public Alerta seeAlert(Long id) {
        Alerta existingAlert = alertaRepository.findById(id).orElse(null);
        try{
            if(existingAlert != null){
                existingAlert.setFechaVisto(ZonedDateTime.now().minusHours(5));
                alertaRepository.save(existingAlert);
            }
        }catch (Exception e){
            return null;
        }
        return existingAlert;
    }


    public boolean sendSmsToFam(Alerta alerta){
        String nombreGestante = alerta.getGestante().getNombres() +" "+alerta.getGestante().getApellidoPaterno() + " "+alerta.getGestante().getApellidoMaterno();
        String emergencyBody = "Su familiar "+nombreGestante+" ha tenido una emergencia";
        String laborBody = "Su familiar "+nombreGestante+" podría estar en labor de parto";
        List<Familiar> familiares = familiarService.findAllByGestante(alerta.getGestante().getId());

        String smsBody = "default message";
        if(alerta.getTipoAlerta().getNombre().equals("EMERGENCIA")){
            smsBody = emergencyBody;
        }else if(alerta.getTipoAlerta().getNombre().equals("LABOR DE PARTO")){
            smsBody = laborBody;
        }

        for(int i = 0; i < familiares.size(); i++){
            smsService.sendSms("+51"+familiares.get(i).getNumeroCelular().toString(), smsBody);
        }

        return true;
    }
}
