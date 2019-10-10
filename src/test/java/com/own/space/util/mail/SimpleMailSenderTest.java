package com.own.space.util.mail;

import freemarker.template.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SimpleMailSenderTest {
    private final static String MAIL_FROM = "noreply@space.com";

    @TestConfiguration
    public static class MailSenderConfig{
        @Bean
        public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean(){
            FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
            factoryBean.setTemplateLoaderPath("/mail-templates/");
            return factoryBean;
        }
    }
    @Autowired
    private Configuration configuration;
    private Mailer senderMock;
    private SimpleMailSender simpleMailSender;


    @Before
    public void setUp() throws Exception {
        senderMock = Mockito.mock(Mailer.class);
        simpleMailSender = new SimpleMailSender(MAIL_FROM,senderMock,configuration);
    }

    @Test
    public void send_correctEmail_shouldSucceed() {
        EmailMessage correctEmail = EmailTestData.createMessageWithCorrectParams();

        simpleMailSender.send(correctEmail,new MessageData("test","data"));
        ArgumentCaptor<EmailMessage> messageArgumentCaptor = ArgumentCaptor.forClass(EmailMessage.class);
        verify(senderMock).send(messageArgumentCaptor.capture());

        EmailMessage sent = messageArgumentCaptor.getValue();
        assertEquals(correctEmail.getEmail(), sent.getEmail());
        assertEquals(correctEmail.getSubject(), sent.getSubject());
        assertEquals(correctEmail.getFrom(), sent.getFrom());
        assertEquals(correctEmail.getEmailBody(), sent.getEmailBody());
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_nullEmail_shouldFail() {
        EmailMessage nullEmail = EmailTestData.createMessageWithNullEmail();
        simpleMailSender.send(nullEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_blankEmail_shouldFail() {
        EmailMessage blankEmail = EmailTestData.createMessageWithBlankEmail();
        simpleMailSender.send(blankEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_nullSubject_shouldFail() {
        EmailMessage nullSubject = EmailTestData.createMessageWithNullSubject();
        simpleMailSender.send(nullSubject);
    }

    @Test(expected = IllegalArgumentException.class)
    public void send_blankSubject_shouldFail() {
        EmailMessage blankSubject = EmailTestData.createMessageWithBlankSubject();
        simpleMailSender.send(blankSubject);
    }
    @Test(expected = IllegalArgumentException.class)
    public void send_nullTemplate_shouldFail() {
        EmailMessage nullTemplate = EmailTestData.createMessageWithNullTemplate();
        simpleMailSender.send(nullTemplate);
    }
    @Test(expected = IllegalArgumentException.class)
    public void send_blankTemplate_shouldFail() {
        EmailMessage blankTemplate = EmailTestData.createMessageWithBlankTemplate();
        simpleMailSender.send(blankTemplate);
    }
}