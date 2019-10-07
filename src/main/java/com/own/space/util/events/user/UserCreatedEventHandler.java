package com.own.space.util.events.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventHandler {
    private final static Logger log = LoggerFactory.getLogger(UserCreatedEventHandler.class);

    @EventListener(UserCreatedEvent.class)
    public void handleEvent(UserCreatedEvent event) {
        log.debug("Handling `{}` registration events", event.getUser().getEmail());
    }
}
