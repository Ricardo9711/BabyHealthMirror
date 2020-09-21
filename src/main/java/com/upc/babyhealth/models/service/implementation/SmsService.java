package com.upc.babyhealth.models.service.implementation;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsService implements com.upc.babyhealth.models.service.SmsService {

    private static final String ACCOUNT_SID = "AC8141eab28f50d894d26bf239ee05a94c";
    private static final String AUTH_TOKEN = "7ab37b86a32ac0a5295013ba7a45e8ee";
    private static final String TWILIO_NUMBER = "+12183043761";

    @Override
    public void sendSms(String recipientNumber, String body) {
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

            // Build a filter for the MessageList
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Body", body));
            params.add(new BasicNameValuePair("To", recipientNumber)); //Add real number here
            params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

            MessageFactory messageFactory = client.getAccount().getMessageFactory();
            Message message = messageFactory.create(params);
            System.out.println(message.getSid());
        }
        catch (TwilioRestException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
