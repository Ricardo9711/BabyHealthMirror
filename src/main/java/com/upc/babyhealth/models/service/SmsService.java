package com.upc.babyhealth.models.service;

public interface SmsService {
    public void sendSms(String recipientNumber, String body);
}
