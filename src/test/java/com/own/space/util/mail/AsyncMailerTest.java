package com.own.space.util.mail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static com.own.space.util.mail.EmailTestData.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AsyncMailerTest {

    private JavaMailSender mailSenderMock;
    private AsyncMailer mailer;

    @Before
    public void setUp() {
        mailSenderMock = mock(JavaMailSender.class);
        mailer = new AsyncMailer(mailSenderMock);
    }
    @Test(expected = IllegalArgumentException.class)
    public void send_nullMessage_shouldFail() {
        mailer.send(null);
    }

    @Test
    public void send_correctMessage_shouldSucceed() {
        EmailMessage message = createEmailToSend();
        mailer.send(message);
        SimpleMailMessage simpleMessage = createSimpleMailMessage();
        verify(mailSenderMock).send(simpleMessage);
    }

}