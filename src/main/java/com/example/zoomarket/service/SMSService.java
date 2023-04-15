package com.example.zoomarket.service;

import org.springframework.stereotype.Service;


@Service
public class SMSService {

    private final SMSHistoryService smsHistoryService;


    public SMSService(SMSHistoryService smsHistoryService) {
        this.smsHistoryService = smsHistoryService;
    }


    public void sendSMS(String phone) {
        //TODO  send sms
        smsHistoryService.addHistory(phone, "2222");
    }


}
