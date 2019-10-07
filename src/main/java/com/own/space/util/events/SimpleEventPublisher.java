package com.own.space.util.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SimpleEventPublisher implements EventPublisher{

    private ApplicationEventPublisher publisher;

    public SimpleEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    @Override
    public void publish(DataEvent event) {
        publisher.publishEvent(event);

    }
}
