package com.example.zoomarket.service;

import com.example.zoomarket.dto.notification.NotificationMessage;
import com.example.zoomarket.dto.notification.NotificationMessageDTO;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    private final ProfileService profileService;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging, ProfileService profileService) {
        this.firebaseMessaging = firebaseMessaging;
        this.profileService = profileService;
    }

    public String sendNotificationByToken(NotificationMessage notificationMessage) {
        Notification notification = Notification.builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();
        Message message = Message
                .builder()
                .setToken(notificationMessage.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessage.getData())
                .build();

        try {
            firebaseMessaging.send(message);
            return "Success Sending Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Error Sending Notification";
        }

    }


    public String sendNotificationToAll(NotificationMessageDTO notificationMessage) {
        List<String> firebaseTokens = profileService.getFirebaseTokens();

        Notification notification = Notification.builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();


        firebaseTokens.forEach(token -> {
            try {
                Message message = Message
                        .builder()
                        .setNotification(notification)
                        .putAllData(notificationMessage.getData())
                        .build();
                firebaseMessaging.send(message);
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        });

        return "Push Notification Sent";

    }
}
