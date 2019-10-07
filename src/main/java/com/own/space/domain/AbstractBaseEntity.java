package com.own.space.domain;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {

    public static final int USERS_SEQ = 1000;
    public static final int DIRECTORIES_SEQ = 10_000;
    public static final int CONTENT_SEQ = 100_000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "abstract_gen")
    protected Integer id;

    protected AbstractBaseEntity() {

    }

    protected AbstractBaseEntity(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return String.format("Entity is %s with id = %s", getClass().getName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return id == null ? 0 : id;
    }
}
