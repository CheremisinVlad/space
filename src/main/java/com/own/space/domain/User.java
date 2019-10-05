package com.own.space.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.own.space.domain.AbstractBaseEntity.SPACEUSER_SEQ;

@Entity
@SequenceGenerator(name="abstract_gen",sequenceName = "users_seq",allocationSize = 1,initialValue = SPACEUSER_SEQ)
@Table(name = "users",uniqueConstraints ={@UniqueConstraint(columnNames = "email",name = "users_unique_email_idx")})
public class User extends AbstractBaseEntity{
    @Column(name = "name")
    @NotNull
    @Size(min = 2,max =30)
    private String name;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    public User() {
    }
    public User(String name, Date registered, String email, String password) {
        this.name = name;
        this.registered = registered;
        this.email = email;
        this.password = password;
    }
    public User(int id, String name) {
        super(id);
        this.name = name;
    }
    public User(int id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User(int id, String name, Date registered, String email, String password) {
        super(id);
        this.name = name;
        this.registered = registered;
        this.email = email;
        this.password = password;
    }
    public User(User user) {
        this(user.getId(),user.getName(),user.getRegistered(),user.getEmail(),user.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", password=" + password +
                '}';
    }
}