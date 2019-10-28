package com.own.space.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractBaseBlock extends AbstractBaseEntity{

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "parent_id")
    @NotNull
    private Integer parentId;

    protected AbstractBaseBlock() {
    }

    protected AbstractBaseBlock(@NotNull Integer userId, @NotNull Integer parentId) {
        this.userId = userId;
        this.parentId = parentId;
    }

    protected  AbstractBaseBlock(int id, @NotNull Integer userId, @NotNull Integer parentId) {
        super(id);
        this.userId = userId;
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractBaseBlock that = (AbstractBaseBlock) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, parentId);
    }

    @Override
    public String toString() {
        return "AbstractBaseBlock{" +
                "userId=" + userId +
                ", parentId=" + parentId +
                ", id=" + id +
                '}';
    }
}
