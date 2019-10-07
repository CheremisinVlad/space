package com.own.space.util.events.user;

import com.own.space.domain.User;
import com.own.space.util.events.DataEvent;
import lombok.NonNull;


public class UserCreatedEvent extends DataEvent {

    private User user;

    public UserCreatedEvent(@NonNull User user) {
        super(user);
        this.user = user;
    }
    public User getUser() {
        return user;
    }
}
