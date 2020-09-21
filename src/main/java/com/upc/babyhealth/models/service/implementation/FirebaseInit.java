package com.upc.babyhealth.models.service.implementation;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FirebaseInit {

    @Value("${app.firebase-config}")
    private String firebaseConfigPath;

    private FirebaseApp firebaseApp;

    @PostConstruct
    private void initialize(){
        try{
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream()))
                    .setDatabaseUrl("https://baby-health-6a700.firebaseio.com")
                    .build();
            if(FirebaseApp.getApps().isEmpty()){
                this.firebaseApp = FirebaseApp.initializeApp(options);
            }
            else{
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
