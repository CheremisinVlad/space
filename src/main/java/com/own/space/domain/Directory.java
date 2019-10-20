package com.own.space.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.own.space.domain.AbstractBaseEntity.DIRECTORIES_SEQ;


@Entity
@Setter
@Getter
@SequenceGenerator(name="abstract_gen",sequenceName = "DIRECTORIES_SEQ",allocationSize = 1,initialValue = DIRECTORIES_SEQ)
@Table(name = "directories",uniqueConstraints ={@UniqueConstraint(columnNames = "user_id",name = "directories_user_id_idx")})
public class Directory extends AbstractBaseBlock{

    @Column(name = "name")
    @Size(min = 2, max = 30)
    @NotNull
    private String name;

    public Directory() {
    }

    public Directory(int id, @Size(min = 2, max = 30) @NotNull String name, @NotNull Integer userId, @NotNull Integer parentId,
                     @NotNull Boolean isMain) {
        super(id, userId, parentId, isMain);
        this.name = name;
    }

    public Directory( @Size(min = 2, max = 30) @NotNull String name,@NotNull Integer userId, @NotNull Integer parentId,
                     @NotNull Boolean isMain) {
        super(userId, parentId, isMain);
        this.name = name;
    }
}
