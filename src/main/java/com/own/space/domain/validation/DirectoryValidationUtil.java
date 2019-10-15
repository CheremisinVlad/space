package com.own.space.domain.validation;

import com.own.space.domain.Directory;
import com.own.space.util.exceptions.InconsistentDataException;
import org.springframework.util.Assert;

public class DirectoryValidationUtil {
    public static void validate(Directory directory) {
        Assert.notNull(directory.getParentId(),"patent id must not be null");
        Assert.notNull(directory.getIsMain(),"main flag must not be null");
        if(directory.getParentId()==0 && directory.getIsMain()==false){
            throw new InconsistentDataException("parent id must not be zero with main window is inactive");
        }else if(directory.getParentId()!=0 && directory.getIsMain()==true){
            throw new InconsistentDataException("parent id must not be not zero with main window is active");
        }
    }
}
