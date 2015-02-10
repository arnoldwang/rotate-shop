package com.dianping.rotate.shop.model.ChartsModel;

import java.util.List;

/**
 * Created by zaza on 15/2/9.
 */
public class XAxis {
    private List<String> categories;
    public XAxis(List<String> categories){
        this.categories = categories;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
