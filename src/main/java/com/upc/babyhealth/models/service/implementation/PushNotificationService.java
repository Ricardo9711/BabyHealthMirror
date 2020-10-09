package com.upc.babyhealth.models.service.implementation;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.upc.babyhealth.models.entity.Alerta;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    public Message createFirebaseMessage(String token, String title, String body){
        return Message.builder()
                .setToken(token)
                .setNotification(
                        new Notification(title, body)
                )
                .putData("content",title)
                .putData("body",body)
                .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                .build();

    }

    public boolean notifyAlert(Alerta alerta, String gestanteToken, String obstetraToken){
        String nombreGestante = alerta.getGestante().getNombres() +" "+alerta.getGestante().getApellidoPaterno() + " "+alerta.getGestante().getApellidoMaterno();
        String title = "Alerta Baby Health";
        String bodyGestanteEmergency = "Mamita, me siento mal, comunícate con mi obstetra.";
        String bodyGestanteLabor = "¡Felicidades! Estás por conocer a tu bebé. Por favor, dirígete al Centro de Salud";
        String bodyObstetraEmergency = "Se han identificado parámetros anormales para la gestante " + nombreGestante + ". Por favor, revisar monitoreo.";
        String bodyObstetraLabor = "La gestante "+nombreGestante+" ha ingresado a labor de parto. Por favor realizar seguimiento.";
        String bodyGestante = "";
        String bodyObstetra = "";

        if(alerta.getTipoAlerta().getNombre().equals("EMERGENCIA")){
            bodyGestante = bodyGestanteEmergency;
            bodyObstetra = bodyObstetraEmergency;
        }else if(alerta.getTipoAlerta().getNombre().equals("LABOR DE PARTO")){
            bodyGestante = bodyGestanteLabor;
            bodyObstetra = bodyObstetraLabor;
        }

        Message messageObstetra = createFirebaseMessage(obstetraToken,title,bodyObstetra);
        Message messageGestante = createFirebaseMessage(gestanteToken,title,bodyGestante);

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

    boolean notifyFinishedMonitoring(String obstetraToken, String nombreGestante){
        String title = "Baby Health";
        String body = "La gestante " + nombreGestante + " ha finalizado un monitoreo.";
        Message messageGestante = createFirebaseMessage(obstetraToken,title,body);

        String response = null;
        try{
            response = FirebaseMessaging.getInstance().send(messageGestante);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return true;
    }
}
