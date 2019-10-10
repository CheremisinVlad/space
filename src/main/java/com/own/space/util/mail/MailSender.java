package com.own.space.util.mail;


public interface MailSender {

    void send(EmailMessage emailMessage,MessageData... params);
}
