package com.own.space.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.own.space.domain.AbstractBaseEntity.RECORDS_SEQ;

@Table(name = "records")
@Getter
@Setter
@Entity
@SequenceGenerator(name = "abstract_gen",sequenceName = "RECORDS_SEQ",allocationSize = 1,initialValue = RECORDS_SEQ)
public class Record extends AbstractBaseBlock {
    
    @Column(name = "name")
    @NotNull
    @Size(min = 2,max = 50)
    private String name;

    @Column(name = "description")
    @NotNull
    @Size(min = 2,max = 200)
    private String description;
    
    @Column(name = "content")
    @NotNull
    @Size(min = 2)
    private String content;

    public Record() {
    }

    public Record(@NotNull Integer userId, @NotNull Integer parentId, @NotNull Boolean isMain, @NotNull @Size(min = 2, max = 50) String name,
                  @NotNull @Size(min = 2, max = 200) String description, @NotNull @Size(min = 2) String content) {
        super(userId, parentId, isMain);
        this.name = name;
        this.description = description;
        this.content = content;
    }

    public Record(int id, @NotNull Integer userId, @NotNull Integer parentId, @NotNull Boolean isMain, @NotNull @Size(min = 2, max = 50) String name,
                  @NotNull @Size(min = 2, max = 200) String description, @NotNull @Size(min = 2) String content) {
        super(id, userId, parentId, isMain);
        this.name = name;
        this.description = description;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}
