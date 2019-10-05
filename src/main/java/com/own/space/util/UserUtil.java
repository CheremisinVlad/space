package com.own.space.util;

import com.own.space.domain.User;
import com.own.space.web.payload.UserTo;

public class UserUtil {
    
    public static UserTo transferUserToUserTransferObject(User user){
        UserTo to = new UserTo();
        to.setUsername(user.getName());
        to.setEmail(user.getEmail());
        to.setPassword(user.getPassword());
        return to;
    }
    
    public static User transferUserTransferObjectToUser(UserTo to){
        User user = new User();
        user.setName(to.getUsername());
        user.setEmail(to.getEmail());
        user.setPassword(to.getPassword());
        return user;
    }
}
