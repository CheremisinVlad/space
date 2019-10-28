package com.own.space.domain;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import static com.own.space.domain.AbstractBaseEntity.URLS_SEQ;

@Entity
@Table(name = "urls")
@SequenceGenerator(name = "abstract_gen",sequenceName = "URLS_SEQ",allocationSize = 1,initialValue = URLS_SEQ)
@Getter
@Setter
public class Url extends AbstractBaseBlock {

    @Column(name = "url")
    @NotNull
    @URL
    private String url;

    @Column(name = "description")
    @NotNull
    @Size(min = 2,max = 50)
    private String description;

    public Url() {
    }

    public Url(int id, @NotNull Integer userId, @NotNull Integer parentId,
               @NotNull @URL String url, @NotNull @Size(min = 2, max = 50) String description) {
        super(id, userId, parentId);
        this.url = url;
        this.description = description;
    }

    public Url(@NotNull Integer userId, @NotNull Integer parentId,
               @NotNull @URL String url, @NotNull @Size(min = 2, max = 50) String description) {
        super(userId, parentId);
        this.url = url;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Url{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
