package com.project.kuzmichev.service.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessage(String to, String subject, String msg);
}
