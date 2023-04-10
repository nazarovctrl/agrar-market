package com.example.zoomarket.controller;

import com.example.zoomarket.dto.notification.NotificationMessage;
import com.example.zoomarket.dto.notification.NotificationMessageDTO;
import com.example.zoomarket.service.FirebaseMessagingService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final FirebaseMessagingService firebaseMessagingService;

    public NotificationController(FirebaseMessagingService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user")
    public String sendNotificationByToken(@Valid @RequestBody NotificationMessage notificationMessage) {
        return firebaseMessagingService.sendNotificationByToken(notificationMessage);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/all")
    public String sendNotification(@Valid @RequestBody NotificationMessageDTO notificationMessage) {
        return firebaseMessagingService.sendNotificationToAll(notificationMessage);
    }

}
