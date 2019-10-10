package com.own.space.util.mail;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AsyncMailer implements Mailer {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncMailer.class);

    private JavaMailSender sender;

    public AsyncMailer(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    @Async
    public void send(EmailMessage message) {
        Assert.notNull(message, "message must not be null");
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            if (StringUtils.isNotBlank(message.getFrom())) {
                mail.setFrom(message.getFrom());
            }
            if (StringUtils.isNotBlank(message.getSubject())) {
                mail.setSubject(message.getSubject());
            }
            if (StringUtils.isNotEmpty(message.getEmailBody())) {
                mail.setText(message.getEmailBody());
            }
            if (message.getEmail() != null) {
                mail.setTo(message.getEmail());
            }
            sender.send(mail);
        } catch (MailException e) {
            LOG .error("Failed to send email", e);
        }

    }
}
