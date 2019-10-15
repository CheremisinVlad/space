package com.own.space.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Objects;

import static com.own.space.domain.AbstractBaseEntity.DIRECTORIES_SEQ;


@Entity
@Setter
@Getter
@SequenceGenerator(name="abstract_gen",sequenceName = "DIRECTORIES_SEQ",allocationSize = 1,initialValue = DIRECTORIES_SEQ)
@Table(name = "directories",uniqueConstraints ={@UniqueConstraint(columnNames = "user_id",name = "directories_user_id_idx")})
public class Directory extends AbstractBaseEntity{

    @Column(name = "name")
    @Size(min = 2,max =30)
    @NotNull
    private String name;

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "parent_id")
    @NotNull
    private Integer parentId;

    @Column(name = "is_main")
    @NotNull
    private Boolean isMain;

    public Directory() {
    }

    public Directory(int id, @Size(min = 2, max = 30) @NotNull String name, @NotNull Integer userId, @NotNull Integer parentId, @NotNull Boolean isMain) {
        super(id);
        this.name = name;
        this.userId = userId;
        this.parentId = parentId;
        this.isMain = isMain;
    }

    public Directory(@Size(min = 2, max = 30) @NotNull String name, @NotNull Integer userId, @NotNull Integer parentId, @NotNull Boolean isMain) {
        this.name = name;
        this.userId = userId;
        this.parentId = parentId;
        this.isMain = isMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Directory directory = (Directory) o;
        return isMain == directory.isMain &&
                Objects.equals(name, directory.name) &&
                Objects.equals(userId, directory.userId) &&
                Objects.equals(parentId, directory.parentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, userId, parentId, isMain);
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", parentId=" + parentId +
                ", isMain=" + isMain +
                ", id=" + id +
                '}';
    }
}
