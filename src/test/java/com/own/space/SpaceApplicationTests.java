package com.own.space;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpaceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @TestConfiguration
    public static class MailSenderConfig{
        @Bean
        public JavaMailSender javaMailSender(){
            return new JavaMailSenderImpl();
        }

    }

}
