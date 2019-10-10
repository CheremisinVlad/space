package com.own.space.util.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleMailSender implements MailSender {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMailSender.class);

    private String mailFrom;
    private Mailer mailer;
    private Configuration configuration;

    public SimpleMailSender(@Value("${app.mail-from}") String mailFrom,
                            Mailer mailer, Configuration configuration) {
        this.mailFrom = mailFrom;
        this.mailer = mailer;
        this.configuration = configuration;
    }

    @Override
    public void send(EmailMessage emailMessage,MessageData... params) {
        //todo
        Assert.hasText(emailMessage.getEmail(), "email must not be blank");
        Assert.hasText(emailMessage.getSubject(), "subject must not be blank");
        Assert.hasText(emailMessage.getTemplate(), "template must not be blank");

        String emailBody = createEmailBody(emailMessage.getTemplate(), params);

        emailMessage.setFrom(mailFrom);
        emailMessage.setEmailBody(emailBody);

        mailer.send(emailMessage);
    }

    private String createEmailBody(String templateName, MessageData... params) {
        try {
            Template template = configuration.getTemplate(templateName);
            Map<String, String> model = new HashMap<>();
            if (params != null) {
                for (MessageData variable : params) {
                    model.put(variable.getKey(), variable.getValue());
                }
            }
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            LOG.error("Failed to create message body from template " + templateName, e);
            return null;
        }
    }
}
