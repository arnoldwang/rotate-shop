package com.dianping.rotate.shop.mapping;

import com.dianping.rotate.shop.model.ModelTest;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zaza on 15/2/2.
 */
public class TestMapping {
    @Autowired
    private Mapper mapper;

    public ModelTest model2Entity(){
        return null;
    }

    public void entity2Model(){
    }
}
