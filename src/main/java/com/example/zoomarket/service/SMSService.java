package com.example.zoomarket.service;

import org.springframework.stereotype.Service;


@Service
public class SMSService {

//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Value("${spring.mail.username}")
    private String fromEmail;



    public void sendSMS(String toAccount, String subject, String text) {
        //TODO  send sms
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(fromEmail);
//        msg.setTo(toAccount);
//        msg.setSubject(subject);
//        msg.setText(text);
//        javaMailSender.send(msg);
    }


}
