package com.own.space.util.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



@Builder
@Data
@AllArgsConstructor
public class EmailMessage {

    private String email;
    private String subject;
    private String template;
    private String from;
    private String emailBody;

    public EmailMessage() {
    }

    public EmailMessage(String email, String subject, String template) {
        this.email = email;
        this.subject = subject;
        this.template = template;
    }
}
