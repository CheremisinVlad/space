package com.own.space.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

import static com.own.space.domain.AbstractBaseEntity.USERS_SEQ;

@Entity
@SequenceGenerator(name="abstract_gen",sequenceName = "USERS_SEQ",allocationSize = 1,initialValue = USERS_SEQ)
@Table(name = "users",uniqueConstraints ={@UniqueConstraint(columnNames = "email",name = "users_unique_email_idx")})
@Setter
@Getter
public class User extends AbstractBaseEntity{
    @Column(name = "name")
    @Size(min = 2,max =30)
    @NotNull
    private String name;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "email")
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email);
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
