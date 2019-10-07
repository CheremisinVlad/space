package com.own.space.util.events;

import org.springframework.context.ApplicationEvent;

public abstract class DataEvent extends ApplicationEvent {

    private static final long serialVersionUID = -32049342304934203L;

    public DataEvent(Object source) {
        super(source);
    }

}
