package com.own.space.util.mail;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {

    void send(Email email);
}
