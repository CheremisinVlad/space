package com.own.space.util.mail;

public class Email {

    private String email;
    private String template;
    private String message;

    public Email(String email, String template, String message) {
        this.email = email;
        this.template = template;
        this.message = message;
    }
}
