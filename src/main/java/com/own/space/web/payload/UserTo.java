package com.own.space.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserTo {
    @NotNull
    @Size(min = 2,max = 40,message = "Username must be between 2 and 40 symbols")
    private String username;

    @NotNull
    @Email(message = "Email address should be valid")
    @Size(max = 100,message = "Email address must be less then 100 symbols")
    private String email;

    @Size(min = 6,max = 30,message = "Password must be between 6 and 30 symbols")
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
