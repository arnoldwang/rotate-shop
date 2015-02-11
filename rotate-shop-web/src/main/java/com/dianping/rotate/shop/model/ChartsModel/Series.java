package com.dianping.rotate.shop.model.ChartsModel;

import java.util.List;

/**
 * Created by zaza on 15/2/9.
 */
public class Series {
    private List<Integer> data;
    public Series(List<Integer> data){
        this.data = data;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
