package com.noux.notificationservice.Notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}