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


    @Override
    public Alerta sendAlert(AlertaRequest alertaRequest) {
        //Debe enviar push notifications a gestante y obstetra
        Alerta alerta = new Alerta();
        Gestante gestante = gestanteService.findOne(alertaRequest.getGestanteId());
        alerta.setGestante(gestante);
        alerta.setTipoAlerta(tipoAlertaService.findByName(alertaRequest.getTipoAlerta()));
        alerta.setIntensidadMmhg(alertaRequest.getIntensidadMmhg());
        alerta.setUsuarioCreacion(alertaRequest.getUsuarioCreacion());
        alerta.setFechaCreacion(ZonedDateTime.now());


        //this.sendPushNotifications(alerta);
        this.sendSmsToFam(alerta);

        return alertaRepository.save(alerta);
    }

    public boolean sendPushNotifications(Alerta alerta, String gestanteToken, String obstetraToken){
        String nombreGestante = alerta.getGestante().getNombres() +" "+alerta.getGestante().getApellidoPaterno() + " "+alerta.getGestante().getApellidoMaterno();
        String title = "Alerta Baby Health";
        String bodyGestanteEmergency = "Mamita, me siento mal ¡Quiero ir al médico!";
        String bodyGestanteLabor = "¡Felicidades! Estás por conocer a tu bebé. Por favor, dirígete al Centro de Salud";
        String bodyObstetraEmergency = "Se han identificado contracciones anormales para la gestante " + nombreGestante + ". Por favor, revisar monitoreo.";
        String bodyObstetraLabor = "La gestante "+nombreGestante+" ha ingresado a labor de parto. Por favor realizar seguimiento.";
        String bodyGestante = "";
        String bodyObstetra = "";

        if(alerta.getTipoAlerta().getNombre().equals("Emergencia")){
            bodyGestante = bodyGestanteEmergency;
            bodyObstetra = bodyObstetraEmergency;
        }else if(alerta.getTipoAlerta().getNombre().equals("Labor de Parto")){
            bodyGestante = bodyGestanteLabor;
            bodyObstetra = bodyObstetraLabor;
        }

        Message messageObstetra = Message.builder()
                .setToken(obstetraToken)
                .setNotification(
                        new Notification(title, bodyObstetra)
                )
                .putData("content",title)
                .putData("body",bodyObstetra)
                .build();

        Message messageGestante = Message.builder()
                .setToken(gestanteToken)
                .setNotification(
                        new Notification(title, bodyGestante)
                )
                .putData("content",title)
                .putData("body",bodyGestante)
                .build();

        String responseObstetra = null;
        String responseGestante = null;
        try{
            responseObstetra = FirebaseMessaging.getInstance().send(messageObstetra);
            responseGestante = FirebaseMessaging.getInstance().send(messageGestante);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean sendSmsToFam(Alerta alerta){
        String nombreGestante = alerta.getGestante().getNombres() +" "+alerta.getGestante().getApellidoPaterno() + " "+alerta.getGestante().getApellidoMaterno();
        String emergencyBody = "Su familiar "+nombreGestante+" ha tenido una emergencia";
        String laborBody = "Su familiar "+nombreGestante+" está en labor de parto";
        List<Familiar> familiares = familiarService.findAllByGestante(alerta.getGestante().getId());

        String smsBody = "default message";
        if(alerta.getTipoAlerta().getNombre().equals("Emergencia")){
            smsBody = emergencyBody;
        }else if(alerta.getTipoAlerta().getNombre().equals("Labor de Parto")){
            smsBody = laborBody;
        }

        for(int i = 0; i < familiares.size(); i++){
            smsService.sendSms("+51"+familiares.get(i).getNumeroCelular().toString(), smsBody);
        }

        return true;
    }
}
