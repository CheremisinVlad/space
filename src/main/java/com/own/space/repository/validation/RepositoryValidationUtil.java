package com.own.space.repository.validation;

import com.own.space.domain.AbstractBaseBlock;
import com.own.space.util.exceptions.InconsistentDataException;
import org.springframework.util.Assert;

public class RepositoryValidationUtil {

    public static <T extends AbstractBaseBlock> void validate(T block) {
        Assert.notNull(block.getParentId(),"parent id must not be null");
        Assert.notNull(block.getIsMain(),"main flag must not be null");
        if(block.getParentId()==0 && block.getIsMain()==false){
            throw new InconsistentDataException("parent id must not be zero with main window is inactive");
        }else if(block.getParentId()!=0 && block.getIsMain()==true){
            throw new InconsistentDataException("parent id must not be not zero with main window is active");
        }
    }
}
