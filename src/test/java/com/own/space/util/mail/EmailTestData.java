package com.own.space.util.mail;

import org.springframework.mail.SimpleMailMessage;

public class EmailTestData {

    private EmailTestData(){

    }

    public static EmailMessage createMessageWithNullEmail() {
        return new EmailMessage(null, "Test subject", "test.ftl");
    }

    public static EmailMessage createMessageWithBlankEmail() {
        return new EmailMessage("    ", "Test subject", "test.ftl");
    }

    public static EmailMessage createMessageWithNullSubject() {
        return new EmailMessage("test@mail.com", null, "Test subject");
    }

    public static EmailMessage createMessageWithBlankSubject() {
        return new EmailMessage("test@mail.com", "    ", "Test subject");
    }

    public static EmailMessage createMessageWithNullTemplate() {
        return new EmailMessage("test@mail.com", "test ", null);
    }

    public static EmailMessage createMessageWithBlankTemplate() {
        return new EmailMessage("test@mail.com", "test ", "    ");
    }

    public static EmailMessage createMessageWithCorrectParams() {
        return new EmailMessage("test@mail.com", "Test subject", "test.ftl","","It's test");
    }

    public static EmailMessage createEmailToSend() {
        return new EmailMessage.EmailMessageBuilder()
                .email("test@mail.com")
                .from("space@mail.com")
                .emailBody("username: test, email: test@space.com")
                .subject("test message")
                .build();
    }

    public static SimpleMailMessage createSimpleMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("space@mail.com");
        simpleMailMessage.setTo("test@mail.com");
        simpleMailMessage.setSubject("test message");
        simpleMailMessage.setText("username: test, email: test@space.com");
        return simpleMailMessage;
    }
}
